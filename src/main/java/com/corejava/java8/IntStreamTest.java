package com.corejava.java8;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by sunitc on 2/14/18.
 */
public class IntStreamTest {

    public static void main(String[] args) {
        int l = 1;
        int r = 19;

        Integer[] result = IntStream.rangeClosed(l, r).filter(i -> i % 2 != 0).boxed().toArray(Integer[]::new);

        System.out.println(result);
        //;
    }
}

