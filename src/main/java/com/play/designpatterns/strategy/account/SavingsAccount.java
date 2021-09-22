package com.play.designpatterns.strategy.account;

import com.play.designpatterns.strategy.interest.InterestService;

import java.util.UUID;

public class SavingsAccount extends BankAccount{

    public SavingsAccount(InterestService interestService, Double interestRate) {
        super(UUID.randomUUID().toString(), interestService, AccountType.SAVINGS, interestRate);
    }
}
