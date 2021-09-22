package com.play.designpatterns.strategy;

import com.play.designpatterns.strategy.account.BankAccount;
import com.play.designpatterns.strategy.account.CurrentAccount;
import com.play.designpatterns.strategy.account.SavingsAccount;
import com.play.designpatterns.strategy.interest.AnnualInterestService;
import com.play.designpatterns.strategy.interest.InterestService;
import com.play.designpatterns.strategy.interest.NoInterestService;
import com.play.designpatterns.strategy.interest.QuarterlyInterestService;

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
