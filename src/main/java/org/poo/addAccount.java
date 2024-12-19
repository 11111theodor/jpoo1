package org.poo;

import org.poo.fileio.CommandInput;

import java.util.List;

public class addAccount implements BankCommand {
    private final List<User> users;
    private final CommandInput command;

    public addAccount(List<User> users, CommandInput command) {
        this.users = users;
        this.command = command;
    }

    public void execute() {
        String email = command.getEmail();
        String accountType = command.getAccountType();
        String currency = command.getCurrency();
        Double interestRate = command.getInterestRate();

        for (User user : users) {
            if (user.getEmail().trim().equals(email.trim())) {
                Account newAccount;
                try {
                    if ("SAVINGS".equalsIgnoreCase(accountType)) {
                        if (interestRate == null) {
                            return;
                        }
                        newAccount = AccountsFactory.createAccount(accountType, email, currency, interestRate);
                    } else {
                        newAccount = AccountsFactory.createAccount(accountType, email, currency);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                    return;
                }

                user.addAccount(newAccount);
                return;
            }
        }
    }
}
