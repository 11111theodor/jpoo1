package org.poo;

import org.poo.fileio.CommandInput;

import java.util.List;

public class addFunds implements BankCommand{
    private final List<User> users;
    private final CommandInput commandInput;

    public addFunds(List<User> users, CommandInput commandInput) {
        this.users = users;
        this.commandInput = commandInput;
    }

    public void execute() {
        String iban = commandInput.getAccount();
        double amount = commandInput.getAmount();

        if (amount <= 0) {
            return;
        }

        for (User user : users) {
            for (Account account : user.getAccounts()) {
                if (account.getIBAN().equals(iban)) {
                    account.deposit(amount);
                    return;
                }
            }
        }
    }
}
