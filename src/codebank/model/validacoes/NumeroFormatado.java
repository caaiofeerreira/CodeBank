package codebank.model.validacoes;

import java.math.BigDecimal;
import java.util.Scanner;

public class NumeroFormatado {

    private final Scanner scanner;

    public NumeroFormatado(Scanner scanner) {
        this.scanner = scanner;
    }

    public BigDecimal validarNumero() {

        if (scanner.hasNextBigDecimal()) {
            BigDecimal saldo = scanner.nextBigDecimal();
            scanner.nextLine();

            if (saldo.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("O numero inserido é negativo. Por favor, insira um numero positivo.");
            }
            return saldo;
        } else {
            throw new IllegalArgumentException("Entrada inválida. Por favor, insira um valor numérico válido.");
        }
    }
}
