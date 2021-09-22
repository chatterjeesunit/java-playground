package com.play.designpatterns.strategy.interest;

public interface InterestService {

    Double calculateInterest(double balanceAmount, int noOfDays, double annualInterestRate);
}
