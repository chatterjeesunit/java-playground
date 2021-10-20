package com.algorithms.others;

/**
 * Compute the [integer] square root of a number.
 * If the number is not a perfect square (there is no integer square root), then it returns -1.
 */
public class SquareRoot {


    public long squareroot(long n) {
        return sqrt(n, 1, n);
    }

    private long sqrt(long num, long min, long max) {

        if(max < min) {
            return -1;
        }
        long nextGuess = (min + max) / 2;
        if(nextGuess * nextGuess == num) {
            return  nextGuess;
        }
        if(nextGuess*nextGuess < num) {
            return sqrt(num, nextGuess+1, max);
        } else {
            return sqrt(num, min, nextGuess-1);
        }

    }
}
