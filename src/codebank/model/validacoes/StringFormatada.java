package codebank.model.validacoes;

import java.util.Scanner;

public class StringFormatada {

    private final Scanner scanner;

    public StringFormatada(Scanner scanner) {
        this.scanner = scanner;
    }

    public String validarString() {

        var nome = scanner.nextLine();

        if (nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Não pode estar vazio. Por favor, insira uma string.");
        } else if (nome.matches(".*\\d.*")) {
            throw new IllegalArgumentException("String inválida. Não deve conter números. Por favor, insira uma string válida.");
        }
        return nome;
    }
}