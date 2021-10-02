package com.designpatterns.strategy.account;

import com.designpatterns.strategy.interest.InterestService;

import java.util.UUID;

public class CurrentAccount extends BankAccount{

    public CurrentAccount(InterestService interestService) {
        super(UUID.randomUUID().toString(), interestService, AccountType.CURRENT, 0.0);
    }
}
