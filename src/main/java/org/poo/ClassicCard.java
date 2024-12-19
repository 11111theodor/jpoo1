package org.poo;

import org.poo.utils.Utils;

public class ClassicCard implements Card {
    private String cardNumber;
    private String status;
    private String tag;

    public ClassicCard() {
        this.cardNumber = Utils.generateCardNumber();
        this.status = "active";
        this.tag = "CLASSIC";
    }

    @Override
    public String getCardNumber() {
        return cardNumber;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public void deactivate() {
        this.status = "inactive";
    }
}
