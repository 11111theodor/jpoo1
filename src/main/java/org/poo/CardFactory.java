package org.poo;

public class CardFactory {
    public static Card createCard(String cardType) {
        if ("CLASSIC".equalsIgnoreCase(cardType)) {
            return new ClassicCard();
        } else if ("ONETIME".equalsIgnoreCase(cardType)) {
            return new OneTimeCard();
        } else {
            throw new IllegalArgumentException("Invalid card type: " + cardType);
        }
    }
}
