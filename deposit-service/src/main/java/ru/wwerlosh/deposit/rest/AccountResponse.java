package ru.wwerlosh.deposit.rest;

import java.time.OffsetDateTime;
import java.util.List;

public class AccountResponse {

    private Long accountId;

    private String name;

    private String email;

    private String phone;

    private List<Long> bills;

    private OffsetDateTime creationDate;

    public AccountResponse(Long accountId, String name, String phone, OffsetDateTime creationDate) {
        this.accountId = accountId;
        this.name = name;
        this.phone = phone;
        this.creationDate = creationDate;
    }

    public AccountResponse() {
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public OffsetDateTime getCreationDate() {
        return creationDate;
    }

    public String getEmail() {
        return email;
    }
}
