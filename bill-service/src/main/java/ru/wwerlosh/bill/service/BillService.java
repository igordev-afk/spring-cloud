package ru.wwerlosh.bill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.wwerlosh.bill.entity.Bill;
import ru.wwerlosh.bill.exceptions.BillNotFoundException;
import ru.wwerlosh.bill.repository.BillRepository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
public class BillService {

    private final BillRepository billRepository;

    @Autowired
    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public Bill getBillById(Long billId) {
        return billRepository.findById(billId)
                .orElseThrow(() -> new BillNotFoundException("Unable to find bill with id: " + billId));
    }

    public Long createBill(Long accountId, BigDecimal amount,
                           Boolean isDefault, OffsetDateTime creationDate,
                           Boolean overdraft) {
        Bill bill = new Bill(accountId, amount, isDefault, OffsetDateTime.now(), overdraft);
        return billRepository.save(bill).getBillId();
    }

    public Bill updateBill(Long billId, Long accountId, BigDecimal amount,
                           Boolean isDefault, OffsetDateTime creationDate,
                           Boolean overdraft) {
        Bill bill = new Bill(accountId, amount, isDefault, overdraft);
        bill.setAccountId(billId);
        return billRepository.save(bill);
    }

    public Bill deleteBill(Long billId) {
        Bill deletedBill = getBillById(billId);
        billRepository.deleteById(billId);
        return deletedBill;
    }
}
