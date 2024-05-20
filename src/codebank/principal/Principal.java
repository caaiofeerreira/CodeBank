package codebank.principal;

import codebank.account.Account;
import codebank.menu.Menu;
import codebank.titular.Titular;
import codebank.transaction.Transaction;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    public void exibirDados (){
        ZoneId saoPauloZone = ZoneId.of("America/Sao_Paulo");
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime saoPauloDateTime = ZonedDateTime.of(localDateTime, saoPauloZone);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Nome do titular: ");

        Titular titular = new Titular();
        titular.setTitular(scanner.nextLine());

        System.out.println("**************************");
        System.out.println("\nNome do titular: " + titular.getTitular());
        System.out.println("Tipo de conta: " + titular.getTipoConta());
        System.out.println("Saldo atual: R$" + titular.getSaldo());
        System.out.println("\n**************************");

        Account account = new Account(titular.getSaldo());

        Menu menu = new Menu(scanner, account);
        menu.exibirMenu();

        System.out.println("\nHistórico de Transações:");
        ArrayList<Transaction> transactions = account.getTransactionHistory();
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
        scanner.close();
    }
}
