package com.pojo.java8;

/**
 * Created by sunitc on 2/14/18.
 */
public class IntStreamTest {

    public static void main(String[] args) {
        int l = 1;
        int r = 19;

        int[] result = java.util.stream.IntStream.rangeClosed(l, r).filter(i -> i%2!=0).boxed().mapToInt(Integer::intValue).toArray();

        System.out.println(result );
        //;
    }
}

