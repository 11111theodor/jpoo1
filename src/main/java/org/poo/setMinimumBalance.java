package org.poo;

import org.poo.fileio.CommandInput;

import java.util.List;

public class setMinimumBalance implements BankCommand {
    private final List<User> users;
    private final CommandInput commandInput;

    public setMinimumBalance(List<User> users, CommandInput commandInput) {
        this.users = users;
        this.commandInput = commandInput;
    }

    public void execute() {
        String iban = commandInput.getAccount();
        double amount = commandInput.getAmount();

        for (User user : users) {
            for (Account account : user.getAccounts()) {
                if (account.getIBAN().equals(iban)) {
                    if (amount >= 0) {
                        account.setMinimumBalance(amount);
                        System.out.println("Minimum balance for account " + iban + " set to " + amount);
                    } else {
                        System.out.println("Invalid minimum balance amount.");
                    }
                    return;
                }
            }
        }
    }
}
