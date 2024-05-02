public class Titular {
    private String titular;
    private String tipoConta = "Corrente";
    double saldo = 1500.00;

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public double getSaldo() {
        return saldo;
    }
}
