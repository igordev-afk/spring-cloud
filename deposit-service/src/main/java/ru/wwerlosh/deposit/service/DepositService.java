package ru.wwerlosh.deposit.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.wwerlosh.deposit.controller.dto.DepositResponse;
import ru.wwerlosh.deposit.entity.Deposit;
import ru.wwerlosh.deposit.exception.DepositServiceException;
import ru.wwerlosh.deposit.repository.DepositRepository;
import ru.wwerlosh.deposit.rest.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
public class DepositService {

    private static final String TOPIC_EXCHANGE_DEPOSIT = "js.deposit.notify.exchange";

    private static final String ROUTING_KEY_DEPOSIT = "js.key.deposit";

    private final DepositRepository depositRepository;

    private final AccountServiceClient accountServiceClient;

    private final BillServiceClient billServiceClient;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public DepositService(DepositRepository depositRepository,
                          AccountServiceClient accountServiceClient,
                          BillServiceClient billServiceClient,
                          RabbitTemplate rabbitTemplate) {
        this.depositRepository = depositRepository;
        this.accountServiceClient = accountServiceClient;
        this.billServiceClient = billServiceClient;
        this.rabbitTemplate = rabbitTemplate;
    }

    public DepositResponse deposit(Long accountId, Long billId, BigDecimal amount) {
        if (accountId == null && billId == null) {
            throw new DepositServiceException("Account is null and bill is null");
        }

        if (billId != null) {
            BillResponse billResponse = billServiceClient.getBillById(billId);
            BillRequest billRequest = convertToBillRequest(billResponse, amount);

            billServiceClient.update(billId, billRequest);

            AccountResponse accountResponse = accountServiceClient.getAccountById(billResponse.getAccountId());
            depositRepository.save(new Deposit(billId, amount, OffsetDateTime.now(), accountResponse.getEmail()));

            DepositResponse depositResponse = new DepositResponse(amount, accountResponse.getEmail());

            return sendResponseToRabbitMQ(depositResponse);
        }

        BillResponse defaultBill = getDefaultBill(accountId);
        BillRequest billRequest = convertToBillRequest(defaultBill, amount);
        billServiceClient.update(defaultBill.getBillId(), billRequest);

        AccountResponse accountResponse = accountServiceClient.getAccountById(accountId);
        depositRepository.save(new Deposit(defaultBill.getBillId(), amount, OffsetDateTime.now(), accountResponse.getEmail()));

        DepositResponse depositResponse = new DepositResponse(amount, accountResponse.getEmail());

        return sendResponseToRabbitMQ(depositResponse);
    }

    private DepositResponse sendResponseToRabbitMQ(DepositResponse depositResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_DEPOSIT, ROUTING_KEY_DEPOSIT, objectMapper.writeValueAsString(depositResponse));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new DepositServiceException("Can't send message to RabbitMQ");
        }
        return depositResponse;
    }

    private BillRequest convertToBillRequest(BillResponse billResponse, BigDecimal amount) {
        BillRequest billRequest = new BillRequest();
        billRequest.setAccountId(billResponse.getAccountId());
        billRequest.setAmount(billResponse.getAmount().add(amount));
        billRequest.setCreationDate(billResponse.getCreationDate());
        billRequest.setIsDefault(billResponse.getIsDefault());
        billRequest.setOverdraftEnabled(billResponse.getOverdraftEnabled());
        return billRequest;
    }

    private BillResponse getDefaultBill(Long accountId) {
        return billServiceClient.getBillsByAccountId(accountId)
                .stream()
                .filter(BillResponse::getIsDefault)
                .findAny()
                .orElseThrow(() -> new DepositServiceException("Unable to find default bill for account: " + accountId));
    }
}
