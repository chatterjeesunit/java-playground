package com.play.corejava.generics;

import java.util.Set;

public class ArithmeticTest {

    public static void main(String[] args) {

        System.out.println("Operation on Integers");
        Set<Integer> set1 = Set.of(1, 2, 3, 5, 27, 125);

        ArithmeticOperation<Integer> intOp = new ArithmeticOperation<>();
        System.out.println(intOp.findCubeElementInList(set1));


        System.out.println("Operation on Longs");
        Set<Long> set2 = Set.of(1l, 2l, 3l, 5l, 27l, 125l);

        ArithmeticOperation<Long> longOp = new ArithmeticOperation<>();
        System.out.println(longOp.findCubeElementInList(set2));
    }
}
