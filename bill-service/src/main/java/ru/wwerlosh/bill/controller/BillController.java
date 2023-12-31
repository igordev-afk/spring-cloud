package ru.wwerlosh.bill.controller;

import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.wwerlosh.bill.controller.dto.BillRequest;
import ru.wwerlosh.bill.controller.dto.BillResponse;
import ru.wwerlosh.bill.service.BillService;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BillController {

    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping("/{billId}")
    public BillResponse getBill(@PathVariable Long billId) {
        return new BillResponse(billService.getBillById(billId));
    }

    @PostMapping("/")
    public Long createBill(@RequestBody BillRequest billRequest) {
        return billService.createBill(
                billRequest.getAccountId(),
                billRequest.getAmount(),
                billRequest.getIsDefault(),
                OffsetDateTime.now(),
                billRequest.getOverdraftEnabled()
        );
    }

    @PutMapping("/{billId}")
    public BillResponse updateBill(@PathVariable Long billId, @RequestBody BillRequest billRequest) {
        return new BillResponse(billService.updateBill(
                billId,
                billRequest.getAccountId(),
                billRequest.getAmount(),
                billRequest.getIsDefault(),
                billRequest.getCreationDate(),
                billRequest.getOverdraftEnabled()
        ));
    }

    @DeleteMapping("/{billId}")
    public BillResponse deleteBill(@PathVariable Long billId) {
        return new BillResponse(billService.deleteBill(billId));
    }

    @GetMapping("/account/{accountId}")
    public List<BillResponse> getBillsByAccountId(@PathVariable Long accountId) {
        return billService.getBillsByAccountId(accountId)
                .stream()
                .map(BillResponse::new)
                .collect(Collectors.toList());
    }
}
