package com.play.algorithms_practice.dynamicprogramming;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MaximumProfitTest {

    MaximumProfit testClass = new MaximumProfit();

    @Test
    void shouldReturnMaximumProfitWhenTraderCanDoMaxPossibleTransactions() {
        int maxTxnsPossible = 2;
        int[] prices = new int[]{10, 22, 5, 75, 65, 80};

        final int profit = testClass.solve(maxTxnsPossible, prices);
        assertThat(profit).isEqualTo(87);
    }


    @Test
    void shouldReturnMaximumProfitWhenTraderCannotDoMaxPossibleTransactions() {
        int maxTxnsPossible = 3;
        int[] prices = new int[]{20, 580, 420, 900};

        final int profit = testClass.solve(maxTxnsPossible, prices);
        assertThat(profit).isEqualTo(1040);
    }


    @Test
    void shouldReturnMaximumProfitWhenTraderCannotDoAnyTransactionsForProfit() {
        int maxTxnsPossible = 1;
        int[] prices = new int[]{100, 90, 80, 50, 25};

        final int profit = testClass.solve(maxTxnsPossible, prices);
        assertThat(profit).isEqualTo(0);
    }

}
