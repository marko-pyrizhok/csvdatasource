package edu.home.csvasdatasource.model;

public class PaymentModel {
    private Long id;
    private float amount;
    private String currency;
    private Long userId;
    private Long targetBankAccountNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
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

    public PaymentModel() {
    }

    public PaymentModel(Long id, float amount, String currency, Long userId, Long targetBankAccountNumber) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
        this.userId = userId;
        this.targetBankAccountNumber = targetBankAccountNumber;
    }
}
