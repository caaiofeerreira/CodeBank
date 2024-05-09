package codebank.menu;

import codebank.account.Account;
import java.util.Scanner;

public class Menu {

    private Account account;
    Scanner scanner;

    public Menu(Scanner scanner, Account account) {
        this.scanner = scanner;
        this.account = account;
    }

    public void exibirMenu() {
        int opcao = 0;

        while (opcao != 5) {
            exibirOpcoes();
            opcao = scanner.nextInt();
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
                    realizarPix();
                    break;
                case 5:
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
        ** DIGITE SUA OPÇÃO **

        1 - Consultar Saldo
        2 - Sacar
        3 - Depositar
        4 - Pix
        5 - Sair
        """);
    }

    private void consultarSaldo() {
        System.out.println("Saldo: R$" + account.getBalance());
    }

    private void sacar() {
        System.out.println("Digite o valor do saque: ");
        double valorSaque = scanner.nextDouble();
        account.saque(valorSaque);
        System.out.println("Saldo atualizado: R$" + account.getBalance() + "\n");
    }

    private void depositar() {
        System.out.println("Digite o valor do depósito: ");
        double valorDeposito = scanner.nextDouble();
        account.deposit(valorDeposito);
        System.out.println("Saldo atualizado: R$" + account.getBalance() + "\n");
    }

    private void realizarPix() {
        Scanner reading = new Scanner(System.in);
        Scanner chavePix = new Scanner(System.in);

        System.out.println("Digite a chave-Pix: ");
        String chave = chavePix.nextLine();
        if (chave.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            System.out.println("Chave PIX válida (Email).");
        } else if (chave.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
            System.out.println("Chave PIX válida (CPF).");
        } else if (chave.matches("\\(?\\d{2}\\)?\\s?\\d{5}-\\d{4}")) {
            System.out.println("Chave PIX válida (Celular).");
        } else {
            System.out.println("Chave PIX inválida. A chave PIX deve ser um Email, CPF ou Celular.");

        }
        System.out.println("\nDigite o valor que deseja enviar:");
        double valorPix = reading.nextDouble();
        System.out.println("\nConfirme a transação via Pix para a chave " + chave + " no valor de R$" + valorPix + " (S/N):");
        String confirmacao = reading.next();
        if (confirmacao.equalsIgnoreCase("S")) {
            if (valorPix > account.getBalance()) {
                System.out.println("Saldo insuficiente para realizar o Pix.");
            } else {
                account.pix(valorPix);
                System.out.println("\nPix de R$" + valorPix + " para a chave " + chave + " realizado com sucesso!");
                System.out.println("Saldo atualizado: R$" + account.getBalance());
                System.out.println("\n");
            }
        } else {
            System.out.println("Transação via Pix cancelada.");
        }
    }
}
