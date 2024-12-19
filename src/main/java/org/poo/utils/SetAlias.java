package org.poo;

import org.poo.fileio.CommandInput;

import java.util.List;

public class SetAlias implements BankCommand {
    private final List<User> users;
    private final CommandInput commandInput;
    private final OutputBuilder outputBuilder;

    public SetAlias(List<User> users, CommandInput commandInput, OutputBuilder outputBuilder) {
        this.users = users;
        this.commandInput = commandInput;
        this.outputBuilder = outputBuilder;
    }

    @Override
    public void execute() {
        String email = commandInput.getEmail();
        String alias = commandInput.getAlias();
        String account = commandInput.getAccount();
        int timestamp = commandInput.getTimestamp();

        User user = findUserByEmail(email);
        if (user == null) {
            outputBuilder.sendAliasError("User not found", timestamp);
            return;
        }

        user.setAlias(alias, account);
        // outputBuilder.sendAliasSuccess("Alias set successfully", timestamp);
    }

    private User findUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
}