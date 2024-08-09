package codebank.principal;

import codebank.model.validacoes.StringFormatada;
import codebank.model.validacoes.NumeroFormatado;
import codebank.services.account.Account;
import codebank.menu.Menu;
import codebank.model.Titular;
import codebank.services.TipoConta;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

    private final Scanner scanner = new Scanner(System.in);
    private final Titular titular = new Titular();
    private final StringFormatada nomeFormatado = new StringFormatada(scanner);
    private final NumeroFormatado saldoFormatado = new NumeroFormatado(scanner);

    public void exibirDados() {

        ZoneId saoPauloZone = ZoneId.of("America/Sao_Paulo");
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime saoPauloDateTime = ZonedDateTime.of(localDateTime, saoPauloZone);

        System.out.println("Nome do titular: ");
        String nome = nomeFormatado.validarString();
        titular.setTitular(nome);

        exibirTipoConta();

        System.out.println("\nSaldo:");
        BigDecimal saldo = saldoFormatado.validarNumero();
        titular.setSaldo(saldo);

        System.out.println("\n*******************************");
        System.out.println("\nNome do titular: " + titular.getTitular());
        System.out.println("Tipo de conta: " + titular.getTipoConta());
        System.out.println("Saldo atual: R$ " + titular.getSaldo());
        System.out.println("\n*******************************");

        Account account = new Account(titular.getSaldo());

        Menu menu = new Menu(scanner, account);
        menu.exibirMenu();

        scanner.close();
    }

    private void exibirTipoConta() {
        int opcao;

        do {
            exibirOpcoesConta();

            try{
                opcao = scanner.nextInt();
                switch (opcao) {
                    case 1:
                        titular.setTipoConta(TipoConta.CONTA_CORRENTE.getConta());
                        System.out.println("Conta Corrente.");
                        return;
                    case 2:
                        titular.setTipoConta(TipoConta.CONTA_POUPANCA.getConta());
                        System.out.println("Conta Poupanca.");
                        return;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, insira um número inteiro.");
                scanner.next();
            }
        } while (true);
    }

    private void exibirOpcoesConta() {
        System.out.println("""

        ***** CODEBANK *****
        
        CONTA:
        1 - CORRENTE
        2 - POUPANCA
        """);
    }
}