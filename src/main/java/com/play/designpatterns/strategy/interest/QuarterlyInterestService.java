package com.play.designpatterns.strategy.interest;

public class QuarterlyInterestService implements InterestService {

    @Override
    public Double calculateInterest(double balanceAmount, int noOfDays, double annualInterestRate) {
        return noOfDays/ 90 * balanceAmount * (annualInterestRate / 4) / 100;
    }
}
