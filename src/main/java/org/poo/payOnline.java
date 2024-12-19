package org.poo;

import org.poo.fileio.CommandInput;

import java.util.List;

public class payOnline implements BankCommand {
    private final List<User> users;
    private final CommandInput commandInput;
    private final OutputBuilder outputBuilder;

    public payOnline(List<User> users, CommandInput commandInput, OutputBuilder outputBuilder) {
        this.users = users;
        this.commandInput = commandInput;
        this.outputBuilder = outputBuilder;
    }

    public void execute() {
        String email = commandInput.getEmail();
        String cardNumber = commandInput.getCardNumber();
        double amount = commandInput.getAmount();
        String currency = commandInput.getCurrency();
        String description = commandInput.getDescription();
        String commerciant = commandInput.getCommerciant();
        int timestamp = commandInput.getTimestamp();

        boolean cardFound = false;

        for (User user : users) {
            if (user.getEmail().trim().equalsIgnoreCase(email.trim())) {
                for (Account account : user.getAccounts()) {
                    for (Card card : account.getCards()) {
                        if (card.getCardNumber().equals(cardNumber)) {
                            cardFound = true;
                            double convertedAmount = amount;

                            if (!account.getCurrency().equalsIgnoreCase(currency)) {
                                convertedAmount = BankTeller.convertCurrency(amount, currency, account.getCurrency());
                            }

                            if (account.getBalance() >= convertedAmount) {
                                account.setBalance(account.getBalance() - convertedAmount);
                                System.out.println("Payment successful: " + description + " to " + commerciant);
                            } /*else {
                                outputBuilder.payOnlineError("Insufficient funds", timestamp);
                            }*/
                            return;
                        }
                    }
                }
            }
        }
        if (!cardFound) {
            outputBuilder.payOnlineError("Card not found", timestamp);
        }
    }
}
