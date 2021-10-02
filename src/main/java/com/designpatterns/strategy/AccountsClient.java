package com.designpatterns.strategy;

import com.designpatterns.strategy.account.BankAccount;
import com.designpatterns.strategy.account.SavingsAccount;
import com.designpatterns.strategy.interest.AnnualInterestService;
import com.designpatterns.strategy.interest.InterestService;
import com.designpatterns.strategy.interest.NoInterestService;
import com.designpatterns.strategy.interest.QuarterlyInterestService;
import com.designpatterns.strategy.account.CurrentAccount;

public class AccountsClient {

    public static void main(String[] args) {

        InterestService quarterlyInterestService = new QuarterlyInterestService();
        InterestService annualInterestService = new AnnualInterestService();
        InterestService noInterestService = new NoInterestService();

        //Savings Account With Quarterly Interest Service
        BankAccount account1 = new SavingsAccount(quarterlyInterestService, 5.5);
        account1.credit(1000);

        //Savings account with Annual Interest Service
        BankAccount account2 = new SavingsAccount(annualInterestService, 5.5);
        account2.credit(1000);

        BankAccount account3 = new CurrentAccount(noInterestService);
        account3.credit(1000);

        account1.printAccountDetails();
        Double interest1 = account1.getInterest(500);
        System.out.println("Interest = " + interest1);

        account2.printAccountDetails();
        Double interest2 = account2.getInterest(500);
        System.out.println("Interest = " + interest2);

        account3.printAccountDetails();
        Double interest3 = account3.getInterest(500);
        System.out.println("Interest = " + interest3);


    }
}
