package org.poo;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private List<Account> accounts;
    private String alias;

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.accounts = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAlias(String alias, String account) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    public void addAccount(Account account) {
        if (account != null) {
            accounts.add(account);
            System.out.println("Account added: " + account.getIBAN());
        } else {
            System.out.println("Cannot add a null account.");
        }
    }

}
