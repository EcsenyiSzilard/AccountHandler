package com.example.Billing;

public enum Currency {
    HUF("HUF"){
        @Override
        public double getValue() {
            return 1L;
        }
    },
    CAD("CAD"){
        @Override
        public double getValue() {
            return 1.25;
        }
    },
    USD("USD"){
        @Override
        public double getValue() {
            return 257.21;
        }
    };

    private final String text;

    Currency(String text) {
        this.text = text;
    }

    public abstract double getValue();
}
