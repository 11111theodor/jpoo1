
package org.poo.utils;

import org.poo.Account;
import org.poo.BankCommand;
import org.poo.OutputBuilder;
import org.poo.User;
import org.poo.fileio.CommandInput;

import java.util.List;

public class SplitPayment implements BankCommand {
    private final List<User> users;
    private final CommandInput commandInput;
    private final OutputBuilder outputBuilder;

    public SplitPayment(List<User> users, CommandInput commandInput, OutputBuilder outputBuilder) {
        this.users = users;
        this.commandInput = commandInput;
        this.outputBuilder = outputBuilder;
    }

    @Override
    public void execute() {
        List<String> accountsForSplit = commandInput.getAccountsForSplit();
        int timestamp = commandInput.getTimestamp();
        String currency = commandInput.getCurrency();
        double amount = commandInput.getAmount();

        double splitAmount = amount / accountsForSplit.size();

        for (String iban : accountsForSplit) {
            Account account = findAccountByIban(iban);
            if (account == null || account.getBalance() < splitAmount) {
                outputBuilder.sendSplitPaymentError("Insufficient funds or account not found", timestamp);
                return;
            }
        }

        for (String iban : accountsForSplit) {
            Account account = findAccountByIban(iban);
            account.setBalance(account.getBalance() - splitAmount);
        }

        outputBuilder.sendSplitPaymentSuccess("Split payment successful", timestamp);
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
}