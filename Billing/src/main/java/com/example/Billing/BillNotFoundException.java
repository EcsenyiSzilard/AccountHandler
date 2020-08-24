package com.example.Billing;

public class BillNotFoundException extends RuntimeException {
    public BillNotFoundException(String accountNumber) {
        super("Could not find bill " + accountNumber);
    }
}
