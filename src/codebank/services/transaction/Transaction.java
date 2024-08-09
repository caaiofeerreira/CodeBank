package codebank.services.transaction;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {

    private Date date;
    private String description;
    private BigDecimal amount;

    public Transaction(Date date, String description, BigDecimal amount) {
        this.date = date;
        this.description = description;
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Data: " + date + ", Descrição: " + description + ", Quantia: R$ " + amount;
    }
}
