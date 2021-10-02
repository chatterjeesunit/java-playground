package com.corejava.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by sunitc on 10/4/17.
 */
public class FlatMapTest {

    public static void main(String[] args) {

        List<Integer> l1 = new ArrayList();
        l1.add(1);
        l1.add(11);
        l1.add(null);
        l1.add(22);
        l1.add(null);

        System.out.println(l1);

        List<Integer> l2 = l1.stream().flatMap(r -> r!=null?Stream.of(r*3): Stream.empty()).collect(Collectors.toList());

        System.out.println(l2);

        List<Integer> l3 = l1.stream().flatMap(r -> r!=null?Stream.of(r*6): Stream.empty()).collect(Collectors.toList());

        System.out.println(l3);

        List<List<Integer>> l4 = new ArrayList<>();
        l4.add(l3);
        l4.add(l2);

        System.out.println(l4);

        List<?> l5 = l4.stream().flatMap(List::stream).collect(Collectors.toList());

        System.out.println(l5);



        //Merge list and remove duplicates
        //l4 is our merged list
        Set<?> l6 = l4.stream().flatMap(List::stream).collect(Collectors.toSet());
        System.out.println(l6);

        List<?> l7 = l5.stream().distinct().collect(Collectors.toList());
        System.out.println(l7);
    }
}
