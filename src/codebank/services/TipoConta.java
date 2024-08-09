package codebank.services;

public enum TipoConta {

    CONTA_CORRENTE("Conta Corrente"),
    CONTA_POUPANCA("Conta Poupança");

    private final String conta;

    TipoConta(String conta) {
        this.conta = conta;
    }

    public String getConta() {
        return conta;
    }
}
