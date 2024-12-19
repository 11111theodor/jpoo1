package org.poo;

import org.poo.fileio.CommandInput;

import java.util.ArrayList;
import java.util.List;

public class deleteCard implements BankCommand {
    private final List<User> users;
    private final CommandInput commandInput;

    public deleteCard(List<User> users, CommandInput commandInput) {
        this.users = users;
        this.commandInput = commandInput;
    }

    public void execute() {
        String email = commandInput.getEmail();
        String cardNumber = commandInput.getCardNumber();

        User user = findUserByEmail(email);
        if (user == null) {
            System.out.println("User not found: " + email);
            return;
        }

        for (Account account : user.getAccounts()) {
            List<Card> updatedCards = new ArrayList<>();
            boolean cardDeleted = false;

            for (Card card : account.getCards()) {
                if (!card.getCardNumber().equals(cardNumber)) {
                    updatedCards.add(card);
                } else {
                    cardDeleted = true;
                }
            }

            if (cardDeleted) {
                account.setCards(updatedCards);
                System.out.println("Card with number " + cardNumber + " has been deleted.");
                return;
            }
        }
        System.out.println("Card not found: " + cardNumber);
    }

    private User findUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().trim().equalsIgnoreCase(email.trim())) {
                return user;
            }
        }
        return null;
    }
}
