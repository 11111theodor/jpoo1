package org.poo;

import java.util.List;

public class printUsers implements BankCommand {
    private final List<User> users;
    private final OutputBuilder outputBuilder;
    private final int timestamp;

    public printUsers(List<User> users, OutputBuilder outputBuilder, int timestamp) {
        this.users = users;
        this.outputBuilder = outputBuilder;
        this.timestamp = timestamp;
    }

    @Override
    public void execute() {
        outputBuilder.printUsers(users, timestamp);
    }
}
