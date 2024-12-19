package org.poo.utils;

public class Transaction {
    private final String accountIban;
    private final String action;
    private final double amount;
    private final String currency;
    private final String description;
    private final int timestamp;
    private final boolean success;
    private final String errorMessage;

    public Transaction(String accountIban, String action, double amount, String currency, String description, int timestamp, boolean success, String errorMessage) {
        this.accountIban = accountIban;
        this.action = action;
        this.amount = amount;
        this.currency = currency;
        this.description = description;
        this.timestamp = timestamp;
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public String getAccountIban() {
        return accountIban;
    }

    public String getAction() {
        return action;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getDescription() {
        return description;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "accountIban='" + accountIban + '\'' +
                ", action='" + action + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", description='" + description + '\'' +
                ", timestamp=" + timestamp +
                ", success=" + success +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}