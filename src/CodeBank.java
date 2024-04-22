import java.util.Scanner;
import java.util.ArrayList;
import java.time.ZoneId;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class CodeBank {
    public static void main(String[] args) {
        ZoneId saoPauloZone = ZoneId.of("America/Sao_Paulo");
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime saoPauloDateTime = ZonedDateTime.of(localDateTime, saoPauloZone);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Nome do titular: ");
        String titular = scanner.nextLine();
        String tipoConta = "Corrente";
        double saldo = 1500.00;

        int opcao = 0;

        String menu = """
        ** DIGITE SUA OPÇÃO **

        1 - Consultar Saldo
        2 - Sacar
        3 - Depositar
        4 - Pix
        5 - Sair
        """;

        System.out.println("**************************");
        System.out.println("\nNome do titular: " + titular);
        System.out.println("Tipo de conta: " + tipoConta);
        System.out.println("Saldo atual: R$" + saldo);
        System.out.println("\n**************************");

        Account account = new Account(saldo);
        Scanner reading = new Scanner(System.in);
        Scanner chavePix = new Scanner(System.in);

        while (opcao != 5) {
            System.out.println(menu);
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    System.out.println("Saldo: R$" + account.getBalance());
                    break;
                case 2:
                    System.out.println("Digite o valor do saque: ");
                    double valorSaque = scanner.nextDouble();
                    account.saque(valorSaque);
                    System.out.println("Saldo atualizado: R$" + account.getBalance() + "\n");
                    break;
                case 3:
                    System.out.println("Digite o valor do depósito: ");
                    double valorDeposito = scanner.nextDouble();
                    account.deposit(valorDeposito);
                    System.out.println("Saldo atualizado: R$" + account.getBalance() + "\n");
                    break;
                case 4:
                    System.out.println("Digite a chave-Pix: ");
                    String chave = chavePix.nextLine();
                    System.out.println("\nDigite o valor que deseja enviar:");
                    double valorPix = reading.nextDouble();
                    System.out.println("\nConfirme a transação via Pix para a chave " + chave + " no valor de R$" + valorPix + " (S/N):");
                    String confirmacao = reading.next();
                    if (confirmacao.equalsIgnoreCase("S")) {
                        if (valorPix > account.getBalance()) {
                            System.out.println("Saldo insuficiente para realizar o Pix.");
                        } else {
                            account.pix(valorPix);
                            System.out.println("\nPix de R$" + valorPix + " para a chave Pix " + chave + " realizado com sucesso!");
                            System.out.println("Saldo atualizado: R$" + account.getBalance());
                            System.out.println("\n");
                        }
                    } else {
                        System.out.println("Transação via Pix cancelada.");
                    }
                    break;
                case 5:
                    System.out.println("Obrigado por usar nossos serviços!");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }

        System.out.println("\nHistórico de Transações:");
        ArrayList<Transaction> transactions = account.getTransactionHistory();
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
        scanner.close();
    }
}