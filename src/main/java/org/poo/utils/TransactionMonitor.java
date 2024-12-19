package org.poo.utils;

import java.util.ArrayList;
import java.util.List;

public class TransactionMonitor {
    private final List<Transaction> transactions;

    public TransactionMonitor() {
        this.transactions = new ArrayList<>();
    }

    public void logTransaction(String accountIban, String action, double amount, String currency, String description, int timestamp, boolean success, String errorMessage) {
        transactions.add(new Transaction(accountIban, action, amount, currency, description, timestamp, success, errorMessage));
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void printTransactions() {
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
}