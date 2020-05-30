package com.play.coursera.algorithmsOne.week1.assignment;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Created by sunitc on 6/4/17.
 */
public class PercolationStats {

    private double[] stats;
    private int trials;


    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
        this.stats = new double[trials];
        this.trials = trials;

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            int row, col;
            while (!percolation.percolates()) {
                row = StdRandom.uniform(n) + 1;
                col = StdRandom.uniform(n) + 1;
                percolation.open(row, col);
            }
            this.stats[i] = percolation.numberOfOpenSites() / Math.pow(n, 2);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(this.stats);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.stats);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean() - (1.96 * this.stddev() / Math.sqrt(this.trials));
    }


    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean() + (1.96 * this.stddev() / Math.sqrt(this.trials));
    }

    // test client (described below)
    public static void main(String[] args) {

    }
}
