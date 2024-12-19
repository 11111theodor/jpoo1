package org.poo;

import org.poo.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ClassicAccount implements Account {
    private String IBAN;
    private double balance;
    private double minBalance;
    private String email;
    private String currency;
    private String accountType;
    private List<Card> cards;

    public ClassicAccount(String email, String currency) {
        this.IBAN = Utils.generateIBAN();
        System.out.println("IBAN: " + IBAN + "\n");
        this.balance = 0.0;
        this.minBalance = 0.0;
        this.email = email;
        this.currency = currency;
        this.accountType = "classic";
        this.cards = new ArrayList<>();
    }

    @Override
    public ArrayList<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public void setMinimumBalance(double minimumBalance) {
        if (minimumBalance >= 0) {
            this.minBalance = minimumBalance;
        } else {
            System.out.println("Minimum balance cannot be negative.");
        }
    }

    public boolean canWithdraw(double amount) {
        return getBalance() - amount >= minBalance;
    }

    @Override
    public void setCards(List<Card> newCards) {
        this.cards = newCards;
    }

    @Override
    public String getIBAN() {
        return IBAN;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    @Override
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String getCurrency() {
        return currency;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getAccountType() {
        return accountType;
    }

    @Override
    public void addCard(String cardType) {
        try {
            Card newCard = CardFactory.createCard(cardType);
            cards.add(newCard);
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding card: " + e.getMessage());
        }
    }
}
