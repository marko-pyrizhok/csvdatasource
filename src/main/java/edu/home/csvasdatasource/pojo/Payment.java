package edu.home.csvasdatasource.pojo;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.*;

@Entity
public class Payment {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @CsvBindByName
    private Long id;

    @CsvBindByName
    private Float amount;

    @CsvBindByName
    private String currency;

    @CsvBindByName
    private Long userId;

    @CsvBindByName
    private Long targetBankAccountNumber;

    public Payment() {
    }

    public Payment(Long id, Float amount, String currency, Long userId, Long targetBankAccountNumber) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
        this.userId = userId;
        this.targetBankAccountNumber = targetBankAccountNumber;
    }

    public Payment(float amount, String currency, Long userId, Long targetBankAccountNumber) {
        this.amount = amount;
        this.currency = currency;
        this.userId = userId;
        this.targetBankAccountNumber = targetBankAccountNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        if (id != payment.id) return false;
        if (Float.compare(payment.amount, amount) != 0) return false;
        if (currency != null ? !currency.equals(payment.currency) : payment.currency != null) return false;
        if (userId != null ? !userId.equals(payment.userId) : payment.userId != null) return false;
        return targetBankAccountNumber != null ? targetBankAccountNumber.equals(payment.targetBankAccountNumber) : payment.targetBankAccountNumber == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (amount != +0.0f ? Float.floatToIntBits(amount) : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (targetBankAccountNumber != null ? targetBankAccountNumber.hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTargetBankAccountNumber() {
        return targetBankAccountNumber;
    }

    public void setTargetBankAccountNumber(Long targetBankAccountNumber) {
        this.targetBankAccountNumber = targetBankAccountNumber;
    }
}
