package com.play.designpatterns.strategy.interest;

public class NoInterestService implements InterestService {
    @Override
    public Double calculateInterest(double balanceAmount, int noOfDays, double annualInterestRate) {
        return 0.0;
    }
}
