package org.poo.utils;

import org.poo.*;
import org.poo.fileio.CommandInput;

import java.util.List;

public class SendMoney implements BankCommand {
    private final List<User> users;
    private final List<exchangeRates> exchangeRates;
    private final CommandInput commandInput;
    private final OutputBuilder outputBuilder;
    private final TransactionMonitor transactionMonitor;

    public SendMoney(List<User> users, List<exchangeRates> exchangeRates, CommandInput commandInput, OutputBuilder outputBuilder, TransactionMonitor transactionMonitor) {
        this.users = users;
        this.exchangeRates = exchangeRates;
        this.commandInput = commandInput;
        this.outputBuilder = outputBuilder;
        this.transactionMonitor = transactionMonitor;
    }

    @Override
    public void execute() {
        String senderIban = commandInput.getAccount();
        double amount = commandInput.getAmount();
        String receiverIban = commandInput.getReceiver();
        int timestamp = commandInput.getTimestamp();
        String description = commandInput.getDescription();

        Account senderAccount = findAccountByIban(senderIban);
        Account receiverAccount = findAccountByIban(receiverIban);

        if (senderAccount == null) {
            transactionMonitor.logTransaction(senderIban, "sendMoney", amount, null, description, timestamp, false,
                    "Sender account not found");
            // outputBuilder.sendMoneyError("Sender account not found", timestamp);
            return;
        }

        if (receiverAccount == null) {
            transactionMonitor.logTransaction(senderIban, "sendMoney", amount, senderAccount.getCurrency(), description,
                    timestamp, true, null);

            return;
        }

        if (senderAccount.getBalance() < amount) {
            transactionMonitor.logTransaction(senderIban, "sendMoney", amount, null, description, timestamp, false,
                    "Sender account not found");
            return;
        }

        double convertedAmount = convertCurrency(amount, senderAccount.getCurrency(), receiverAccount.getCurrency());
        transactionMonitor.logTransaction(senderIban, "sendMoney", amount, senderAccount.getCurrency(), description, timestamp, true, null);

        double senderNewBalance = senderAccount.getBalance() - amount;
        double receiverNewBalance = receiverAccount.getBalance() + convertedAmount;
        senderAccount.setBalance(senderNewBalance);
        receiverAccount.setBalance(receiverNewBalance);

    }

    private Account findAccountByIban(String iban) {
        for (User user : users) {
            for (Account account : user.getAccounts()) {
                if (account.getIBAN().equals(iban)) {
                    return account;
                }
            }
        }
        return null;
    }

    private double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        if (fromCurrency.equals(toCurrency)) {
            return amount;
        }
        for (exchangeRates rate : exchangeRates) {
            if (rate.getFrom().equals(fromCurrency) && rate.getTo().equals(toCurrency)) {
                return amount * rate.getRate();
            } else if (rate.getFrom().equals(toCurrency) && rate.getTo().equals(fromCurrency)) {
                return amount / rate.getRate();
            }
        }
        // Try to find an intermediate currency for conversion
        for (exchangeRates rate : exchangeRates) {
            if (rate.getFrom().equals(fromCurrency)) {
                double intermediateAmount = amount * rate.getRate();
                return convertCurrency(intermediateAmount, rate.getTo(), toCurrency);
            } else if (rate.getTo().equals(fromCurrency)) {
                double intermediateAmount = amount / rate.getRate();
                return convertCurrency(intermediateAmount, rate.getFrom(), toCurrency);
            }
        }
        throw new IllegalArgumentException("Exchange rate not found for " + fromCurrency + " to " + toCurrency);
    }
}