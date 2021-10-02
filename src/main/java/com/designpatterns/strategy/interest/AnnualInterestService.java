package com.designpatterns.strategy.interest;

public class AnnualInterestService implements InterestService {
    @Override
    public Double calculateInterest(double balanceAmount, int noOfDays, double annualInterestRate) {
        return noOfDays/ 365 * balanceAmount * annualInterestRate / 100;
    }
}
