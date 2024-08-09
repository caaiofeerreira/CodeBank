package codebank.menu;

import codebank.model.validacoes.NumeroFormatado;
import codebank.model.validacoes.StringFormatada;
import codebank.services.account.Account;
import codebank.services.transaction.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private final Account account;
    private Scanner scanner = new Scanner(System.in);
    private final StringFormatada stringFormatada = new StringFormatada(scanner);
    private final NumeroFormatado numeroFormatado = new NumeroFormatado(scanner);

    public Menu(Scanner scanner, Account account) {

        this.scanner = scanner;
        this.account = account;
    }

    public void exibirMenu() {

        int opcao = 0;

        while (opcao != 7) {

            exibirOpcoes();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    consultarSaldo();
                    break;
                case 2:
                    sacar();
                    break;
                case 3:
                    depositar();
                    break;
                case 4:
                    pix();
                    break;
                case 5:
                     transferir();
                    break;
                case 6:
                    extrato();
                    break;
                case 7:
                    System.out.println("Obrigado por usar nossos serviços!");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }

    private void exibirOpcoes() {
        System.out.println("""
        
        ***** BEM-VINDO AO CODEBANK *****
        
        DIGITE SUA OPÇÃO:

        1 - Consultar Saldo
        2 - Sacar
        3 - Depositar
        4 - Pix
        5 - Transferir
        6 - Historico de Transacoes
        7 - Sair
        """);
    }

    private void consultarSaldo() {

        System.out.println("Saldo: R$ " + account.getBalance());
    }

    private void sacar() {

        System.out.println("Digite o valor do saque: ");
        BigDecimal valorSaque = numeroFormatado.validarNumero();
        account.saque(valorSaque);
        System.out.println("Saldo atualizado: R$ " + account.getBalance() + "\n");
    }

    private void depositar() {

        System.out.println("Digite o valor do depósito: ");
        BigDecimal valorDeposito = numeroFormatado.validarNumero();
        account.deposita(valorDeposito);
        System.out.println("Saldo atualizado: R$ " + account.getBalance() + "\n");
    }

    private void pix() {

        System.out.println("Digite a chave-Pix: ");
        var chave = scanner.nextLine();

        if (chave.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            System.out.println("Chave PIX válida (Email).");
        } else if (chave.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
            System.out.println("Chave PIX válida (CPF).");
        } else if (chave.matches("\\(?\\d{2}\\)?\\s?\\d{5}-\\d{4}")) {
            System.out.println("Chave PIX válida (Celular).");
        } else {
            System.out.println("Chave PIX inválida. A chave PIX deve ser um Email, CPF ou Celular.");
            return;
        }

        System.out.println("\nDigite o valor que deseja enviar:");
        BigDecimal valorPix = numeroFormatado.validarNumero();
        System.out.println("\nConfirme a transação via Pix para a chave " + chave + " no valor de R$" + valorPix + " (S/N):");
        var confirmacao = stringFormatada.validarString();

        if (confirmacao.equalsIgnoreCase("S")) {
            if (valorPix.compareTo(account.getBalance()) > 0) {
                System.out.println("Saldo insuficiente para realizar o Pix.");
            } else {
                account.pix(valorPix);
                System.out.println("\nPix de R$" + valorPix + " para a chave " + chave + " realizado com sucesso!");
                System.out.println("Saldo atualizado: R$ " + account.getBalance());
                System.out.println("\n");
            }
        } else {
            System.out.println("Transação via Pix cancelada.");
        }
    }

    private void transferir() {

        System.out.println("Transferir para:");
        var nomeRecebedor = stringFormatada.validarString();


        System.out.println("Numero da conta:");
        var numeroContaRecebedor = scanner.nextLine();

        System.out.println("Digite o valor que deseja transferir:");
        BigDecimal valorTransferencia = numeroFormatado.validarNumero();

        if (!numeroContaRecebedor.matches("\\d{1,12}\\-\\d{1}")) {
            System.out.println("Numero de conta invalido.");
            return;
        }

        System.out.println("\nConfirme a transferência para: " + nomeRecebedor + ", conta: " + numeroContaRecebedor + ", no valor de R$" + valorTransferencia + " (S/N):");
        var confirmacao = scanner.nextLine();

        if (confirmacao.equalsIgnoreCase("S")) {
            if (valorTransferencia.compareTo(account.getBalance()) > 0) {
                System.out.println("Saldo insuficiente para realizar a transferencia.");
            } else {
                account.transferir(valorTransferencia);
                System.out.println("\nTransferencia de R$ " + valorTransferencia + " para: " + nomeRecebedor + ", conta " + numeroContaRecebedor + ", realizado com sucesso!");
                System.out.println("Saldo atualizado: R$ " + account.getBalance());
            }
        } else {
            System.out.println("Transferencia cancelada.");
        }
    }

    private void extrato() {

        ArrayList<Transaction> transactions = account.getTransactionHistory();

        if (!transactions.isEmpty()) {
            System.out.println("\nHistórico de Transações:");

            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }

        } else {
            System.out.println("\nHistórico de Transações:");
            System.out.println("Nenhuma transacao encontrada.");
        }
    }
}