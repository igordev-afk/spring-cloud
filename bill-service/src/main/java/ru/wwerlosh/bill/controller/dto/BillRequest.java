package ru.wwerlosh.bill.controller.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class BillRequest {

    private Long accountId;

    private BigDecimal amount;

    private Boolean isDefault;

    private OffsetDateTime creationDate;

    private Boolean overdraftEnabled;

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
