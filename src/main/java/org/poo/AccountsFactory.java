package org.poo;

public class AccountsFactory {
    public static Account createAccount(String accountType, String email, String currency, Double... extraParams) {
        if ("CLASSIC".equalsIgnoreCase(accountType)) {
            return new ClassicAccount(email, currency);
        } else if ("SAVINGS".equalsIgnoreCase(accountType)) {
            if (extraParams.length > 0) {
                return new SavingsAccount(currency, extraParams[0]);
            } else {
                throw new IllegalArgumentException("SavingsAccount requires an interest rate.");
            }
        } else {
            throw new IllegalArgumentException("Invalid account type.");
        }
    }
}
