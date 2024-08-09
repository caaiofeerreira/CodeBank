package codebank.services.account;

import codebank.services.transaction.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class Account {

    private BigDecimal balance;
    private BigDecimal transferir;
    private ArrayList<Transaction> transactionHistory;

    public Account(BigDecimal initialBalance) {

        balance = initialBalance;
        transactionHistory = new ArrayList<>();
    }

    public void deposita(BigDecimal amount) {
        balance = balance.add(amount);
        Transaction transaction = new Transaction(new Date(), "Depósito", amount);
        transactionHistory.add(transaction);
    }

    public void transferir(BigDecimal amount) {

        if (balance.compareTo(amount) > 0) {
            balance = balance.subtract(amount);
            Transaction transaction = new Transaction(new Date(), "Transferencia", amount.negate());
            transactionHistory.add(transaction);
        } else {
            System.out.println("Saldo insuficiente");
        }
    }

    public void saque(BigDecimal amount) {

        if (balance.compareTo(amount) > 0) {
            balance = balance.subtract(amount);
            Transaction transaction = new Transaction(new Date(), "Saque", amount.negate());
            transactionHistory.add(transaction);
        } else {
            System.out.println("Saldo insuficiente");
        }
    }

    public void pix(BigDecimal amount) {

        if (balance.compareTo(amount) >= 0) {
            balance = balance.subtract(amount);
            Transaction transaction = new Transaction(new Date(), "Pix", amount.negate());
            transactionHistory.add(transaction);
        } else {
            System.out.println("Saldo insuficiente");
        }
    }

    public ArrayList<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}