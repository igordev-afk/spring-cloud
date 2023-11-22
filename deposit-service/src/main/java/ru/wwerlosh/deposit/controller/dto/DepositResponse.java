package ru.wwerlosh.deposit.controller.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class DepositResponse {

    private BigDecimal amount;

    private String mail;

    public DepositResponse(BigDecimal amount, String mail) {
        this.amount = amount;
        this.mail = mail;
    }

    public DepositResponse() {
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
