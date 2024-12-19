package org.poo;

import java.util.ArrayList;
import java.util.List;

public interface Account {
    String getIBAN();

    String getEmail();

    double getBalance();

    String getCurrency();

    String getAccountType();

    void addCard(String cardType);

    void setMinimumBalance(double minimumBalance);

    boolean canWithdraw(double amount);

    ArrayList<Card> getCards();

    void deposit(double amount);

    void setBalance(double balance);

    void setCards(List<Card> newCards);
}
