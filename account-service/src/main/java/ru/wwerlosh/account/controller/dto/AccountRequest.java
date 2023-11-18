package ru.wwerlosh.account.controller.dto;

import java.time.OffsetDateTime;
import java.util.List;

public class AccountRequest {

    private String name;

    private String email;

    private String phone;

    private List<Long> bills;

    private OffsetDateTime creationDate;

    public AccountRequest() {
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public OffsetDateTime getCreationDate() {
        return creationDate;
    }

    public List<Long> getBills() {
        return bills;
    }
}
