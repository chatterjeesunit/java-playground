package com.play.ds_algo.gfg.practice;

public class HCF {

    public long solveRecursively(long n, long m) {
       return findHCFRecursively(n, m, 1l);
    }

    private long findHCFRecursively(long n, long m, long hcf) {
        long min = Math.min(n, m);
        if(m%min == 0 && n%min == 0) {
            return min;
        }
        for (long i = 2; i <= min; i++) {
            long remainderM = m % i;
            long remainderN = n % i;

            if(remainderM == 0 && remainderN == 0) {
                return findHCFRecursively(m/i, n/i, hcf*i);
            }
        }
        return hcf;
    }


    public long solveWithoutRecursion(long n, long m) {

        boolean hcfFound = false;
        long hcf = 1l;
        long divisor = m > n ? n : m;
        long dividend = m > n ? m : n;
        while(!hcfFound) {
            long remainder = dividend % divisor;
            if(remainder == 0) {
                hcf = divisor;
                hcfFound = true;
            }
            dividend = divisor;
            divisor = remainder;
        }
        return hcf;
    }
}
