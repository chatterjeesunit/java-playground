package com.corejava.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by sunitc on 3/28/18.
 */
public class ListConcatAndDistinct {

    public static void main(String[] args) {

        List<Integer> listOne = Arrays.asList(1, 2, 3, 4, 5 );
        List<Integer> listTwo = Arrays.asList(2, 4, 6, 8, 10);

        List<Integer> results = Stream.concat(listOne.stream(), listTwo.stream()).distinct().collect(Collectors.toList());
        System.out.println(results);
    }
}

