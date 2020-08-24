package com.example.Billing;

public class Transaction {
    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    long value;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    String currency;

    public Transaction(long value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    String accountNumber;
}
