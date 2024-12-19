package org.poo;

import org.poo.fileio.CommandInput;

import java.util.List;

public class createCard implements BankCommand{
    private final List<User> users;
    private final CommandInput commandInput;

    public createCard(List<User> users, CommandInput commandInput) {
        this.users = users;
        this.commandInput = commandInput;
    }

    public void execute() {
        String accountIBAN = commandInput.getAccount();
        String email = commandInput.getEmail();
        String commandType = commandInput.getCommand();
        String cardType;

        if ("createCard".equalsIgnoreCase(commandType)) {
            cardType = "CLASSIC";
        } else if ("createOneTimeCard".equalsIgnoreCase(commandType)) {
            cardType = "ONETIME";
        } else {
            return;
        }

        for (User user : users) {
            if (user.getEmail().trim().equals(email.trim())) {
                for (Account account : user.getAccounts()) {
                    if (account.getIBAN().equals(accountIBAN)) {
                        account.addCard(cardType);
                        return;
                    }
                }
                return;
            }
        }
    }
}
