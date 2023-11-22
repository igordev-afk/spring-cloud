package ru.wwerlosh.notification.service;

import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;

public class DepositResponse {

    private BigDecimal amount;

    private String mail;

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
