package org.poo;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.poo.fileio.CommandInput;
import org.poo.fileio.ExchangeInput;
import org.poo.fileio.ObjectInput;
import org.poo.fileio.UserInput;
import org.poo.utils.SendMoney;
import org.poo.utils.SplitPayment;
import org.poo.utils.Utils;

import java.util.ArrayList;
import java.util.List;


public class BankTeller {
    private final OutputBuilder outputBuilder;
    private List<User> users;
    private static List<exchangeRates> exchangeRates;
    private int timestamp;

    public BankTeller() {
        this.outputBuilder = new OutputBuilder();
        this.users = new ArrayList<>();
        this.exchangeRates = new ArrayList<>();
        this.timestamp = 0;
    }

    public void startDay(ObjectInput inputData) {
        Utils.resetRandom();
        for (UserInput userInput : inputData.getUsers()) {
            User user = new User(userInput.getFirstName(), userInput.getLastName(), userInput.getEmail());
            users.add(user);
        }

        for (ExchangeInput rateInput : inputData.getExchangeRates()) {
            exchangeRates rate = new exchangeRates();
            rate.setFrom(rateInput.getFrom());
            rate.setTo(rateInput.getTo());
            rate.setRate(rateInput.getRate());
            exchangeRates.add(rate);
        }

        for (CommandInput command : inputData.getCommands()) {
            String commandName = command.getCommand();
            timestamp++;
            if (commandName.equals("printUsers")) {
                printUsers printUsersCommand = new printUsers(users, outputBuilder, timestamp);
                printUsersCommand.execute();
            } else if (commandName.equals("addAccount")) {
                addAccount addAccountCommand = new addAccount(users, command);
                addAccountCommand.execute();
            } else if (commandName.equals("createCard") || commandName.equals("createOneTimeCard")) {
                createCard createCardCommand = new createCard(users, command);
                createCardCommand.execute();
            } else if (commandName.equals("addFunds")) {
                addFunds addFundsCommand = new addFunds(users, command);
                addFundsCommand.execute();
            } else if (commandName.equals("deleteAccount")) {
                deleteAccount deleteAccountCommand = new deleteAccount(users, command, outputBuilder);
                deleteAccountCommand.execute();
            } else if (commandName.equals("deleteCard")) {
                deleteCard deleteCardCommand = new deleteCard(users, command);
                deleteCardCommand.execute();
            } else if (commandName.equals("setMinimumBalance")) {
                setMinimumBalance setMinimumBalanceCommand = new setMinimumBalance(users, command);
                setMinimumBalanceCommand.execute();
            } else if (commandName.equals("payOnline")) {
                payOnline payOnlineCommand = new payOnline(users, command, outputBuilder);
                payOnlineCommand.execute();
            } else if (commandName.equals("sendMoney")) {
                SendMoney sendMoneyCommand = new SendMoney(users, exchangeRates, command, outputBuilder);
                sendMoneyCommand.execute();
            } else if (commandName.equals("setAlias")) {
                org.poo.SetAlias setAliasCommand = new org.poo.SetAlias(users, command, outputBuilder);
                setAliasCommand.execute();
                //setAlias(command);
            } else if (commandName.equals("getAccountsForSplit")) {
                SplitPayment splitPaymentCommand = new SplitPayment(users, command, outputBuilder);
                splitPaymentCommand.execute();
                //printAccountsForSplit(command, outputBuilder);
            } else if (commandName.equals("splitPayment")) {
                SplitPayment splitPaymentCommand = new SplitPayment(users, command, outputBuilder);
                splitPaymentCommand.execute();
                //splitPayment(command, outputBuilder);
            }

        }

    }

    public ArrayNode getOutput() {
        return outputBuilder.getOutput();
    }


    public static double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        if (fromCurrency.equalsIgnoreCase(toCurrency)) {
            return amount;
        }

        for (exchangeRates rate : exchangeRates) {
            if (rate.getFrom().equalsIgnoreCase(fromCurrency) && rate.getTo().equalsIgnoreCase(toCurrency)) {
                return amount * rate.getRate();
            }
        }

        for (exchangeRates rate : exchangeRates) {
            if (rate.getFrom().equalsIgnoreCase(toCurrency) && rate.getTo().equalsIgnoreCase(fromCurrency)) {
                return amount / rate.getRate();
            }
        }
        return amount;
    }

    public User findUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().trim().equalsIgnoreCase(email.trim())) {
                return user;
            }
        }
        return null;
    }

    public Account findAccountByIBAN(User user, String iban) {
        for (Account account : user.getAccounts()) {
            if (account.getIBAN().equals(iban)) {
                return account;
            }
        }
        return null;
    }

    public Card findCardByNumber(Account account, String cardNumber) {
        for (Card card : account.getCards()) {
            if (card.getCardNumber().equals(cardNumber)) {
                return card;
            }
        }
        return null;
    }

}
