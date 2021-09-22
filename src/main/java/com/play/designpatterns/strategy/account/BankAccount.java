package com.play.designpatterns.strategy.account;

import com.play.designpatterns.strategy.interest.InterestService;

public class BankAccount {

    private String accountNumber;
    private Double balance;
    private AccountType accountType;
    private InterestService interestService;
    private Double annualInterestRate;

    public BankAccount(String accountNumber, InterestService interestService, AccountType accountType,
                       Double annualInterestRate) {
        this.accountNumber = accountNumber;
        this.interestService = interestService;
        this.accountType = accountType;
        this.annualInterestRate = annualInterestRate;
        this.balance = Double.valueOf(0);
    }

    public void credit(double amount) {
        this.balance += amount;
    }

    public void debit(double amount) {
        this.balance -= amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setAnnualInterestRate(Double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public void printAccountDetails() {
        System.out.println("Balance in " + getAccountType() + " accounts # " + getAccountNumber() + ", is Rs. " + getBalance());
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public Double getInterest(int days) {
        return interestService.calculateInterest(balance, days, annualInterestRate );
    }
}
