package ru.wwerlosh.deposit.rest;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class BillResponse {

    private Long billId;

    private Long accountId;

    private BigDecimal amount;

    private Boolean isDefault;

    private OffsetDateTime creationDate;

    private Boolean overdraftEnabled;

    public BillResponse(Long billId, Long accountId, BigDecimal amount, Boolean isDefault, OffsetDateTime creationDate, Boolean overdraftEnabled) {
        this.billId = billId;
        this.accountId = accountId;
        this.amount = amount;
        this.isDefault = isDefault;
        this.creationDate = creationDate;
        this.overdraftEnabled = overdraftEnabled;
    }

    public BillResponse() {
    }

    public Long getBillId() {
        return billId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public OffsetDateTime getCreationDate() {
        return creationDate;
    }

    public Boolean getOverdraftEnabled() {
        return overdraftEnabled;
    }
}
