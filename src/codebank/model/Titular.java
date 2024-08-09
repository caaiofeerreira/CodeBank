package codebank.model;

import java.math.BigDecimal;

public class Titular {

    private String titular;
    private String tipoConta;
    private BigDecimal saldo;

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public BigDecimal setSaldo(BigDecimal saldo) {
        return this.saldo = saldo;
    }
}