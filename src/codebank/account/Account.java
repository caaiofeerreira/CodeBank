package codebank.account;

import codebank.transaction.Transaction;

import java.util.ArrayList;
import java.util.Date;

public class Account {

    private double balance;
    private ArrayList<Transaction> transactionHistory;

    public Account(double initialBalance) {
        balance = initialBalance;
        transactionHistory = new ArrayList<>();
    }

    public void deposit(double amount) {
        balance += amount;
        Transaction transaction = new Transaction(new Date(), "Depósito", amount);
        transactionHistory.add(transaction);
    }

    public void saque(double amount) {
        if (balance >= amount) {
            balance -= amount;
            Transaction transaction = new Transaction(new Date(), "Saque", -amount);
            transactionHistory.add(transaction);
        } else {
            System.out.println("Saldo insuficiente");
        }
    }

    public void pix(double amount) {
        if (balance >= amount) {
            balance -= amount;
            Transaction transaction = new Transaction(new Date(), "Pix", -amount);
            transactionHistory.add(transaction);
        } else {
            System.out.println("Saldo insuficiente");
        }
    }

    public ArrayList<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public double getBalance() {
        return balance;
    }
}