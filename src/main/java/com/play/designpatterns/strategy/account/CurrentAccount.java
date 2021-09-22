package com.play.designpatterns.strategy.account;

import com.play.designpatterns.strategy.interest.InterestService;
import com.play.designpatterns.strategy.interest.NoInterestService;

import java.util.UUID;

public class CurrentAccount extends BankAccount{

    public CurrentAccount(InterestService interestService) {
        super(UUID.randomUUID().toString(), interestService, AccountType.CURRENT, 0.0);
    }
}
