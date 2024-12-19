package org.poo;

import org.poo.fileio.CommandInput;

import java.util.List;

public class deleteAccount implements BankCommand {
    private final List<User> users;
    private final CommandInput commandInput;
    private final OutputBuilder outputBuilder;

    public deleteAccount(List<User> users, CommandInput commandInput, OutputBuilder outputBuilder) {
        this.users = users;
        this.commandInput = commandInput;
        this.outputBuilder = outputBuilder;
    }

    public void execute() {
        String email = commandInput.getEmail();
        String iban = commandInput.getAccount();
        int timestamp = commandInput.getTimestamp();

        for (User user : users) {
            if (user.getEmail().trim().equals(email.trim())) {
                List<Account> accounts = user.getAccounts();
                for (Account account : accounts) {
                    if (account.getIBAN().equals(iban)) {
                        accounts.remove(account);
                        outputBuilder.printDeleteAccountSuccess(timestamp);
                        return;
                    }
                }
                return;
            }
        }
    }
}
