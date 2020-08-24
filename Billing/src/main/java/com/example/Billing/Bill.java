package com.example.Billing;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Bill {

    private @Id @GeneratedValue Long id;
    private String accountNumber;
    private String currency;
    private double amount;

    Bill() {}

    Bill(String accountNumber, String currency, double amount) {

        this.accountNumber = accountNumber;
        this.currency = currency;
        this.amount = amount;
    }

    public Long getId() {
        return this.id;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Bill))
            return false;
        Bill Bill = (Bill) o;
        return Objects.equals(this.id, Bill.id) && Objects.equals(this.accountNumber, Bill.accountNumber)
                && Objects.equals(this.currency, Bill.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.accountNumber, this.currency);
    }

    @Override
    public String toString() {
        return "Bill{" + "id=" + this.id + ", accountNumber='" + this.accountNumber + '\'' + ", currency='" + this.currency + '\'' + '}';
    }
}