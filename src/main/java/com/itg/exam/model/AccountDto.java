package com.itg.exam.model;

import jakarta.persistence.*;

@Entity
@Table(name = "account")
public class AccountDto {
    @Id
    @SequenceGenerator(
            name = "seq_account_id",
            sequenceName = "seq_account_id",
            allocationSize= 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_account_id"
    )
    private Long accountNumber;
    private Double availableBalance;
    private String accountType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerNumber", referencedColumnName = "customerNumber")
    private CustomerDto customer;

    public AccountDto() {
        super();
    }

    public AccountDto(Long accountNumber, Double availableBalance, String accountType, CustomerDto customer) {
        this.accountNumber = accountNumber;
        this.availableBalance = availableBalance;
        this.accountType = accountType;

    }

    public AccountDto(Double availableBalance, String accountType, CustomerDto customer) {
        this.availableBalance = availableBalance;
        this.accountType = accountType;
        this.customer = customer;
    }

    public AccountDto(Long accountNumber, Double availableBalance, String accountType) {
        this.accountNumber = accountNumber;
        this.availableBalance = availableBalance;
        this.accountType = accountType;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(Double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public String getAccountType() {
        if (this.accountType.equals("S")) {
            return "Savings";
        } else if (this.accountType.equals("C")){
            return "Checking";
        }else{
            return this.accountType;
        }

    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

}
