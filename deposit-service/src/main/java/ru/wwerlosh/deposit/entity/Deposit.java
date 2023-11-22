package ru.wwerlosh.deposit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long depositId;

    private Long billId;

    private BigDecimal amount;

    private OffsetDateTime date;

    private String mail;

    public Deposit(Long billId, BigDecimal amount, OffsetDateTime date, String mail) {
        this.billId = billId;
        this.amount = amount;
        this.date = date;
        this.mail = mail;
    }

    public Deposit() {
    }

    public Long getDepositId() {
        return depositId;
    }

    public void setDepositId(Long depositId) {
        this.depositId = depositId;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    public String getEmail() {
        return mail;
    }

    public void setEmail(String email) {
        this.mail = email;
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "depositId=" + depositId +
                ", billId=" + billId +
                ", amount=" + amount +
                ", date=" + date +
                ", email='" + mail + '\'' +
                '}';
    }
}
