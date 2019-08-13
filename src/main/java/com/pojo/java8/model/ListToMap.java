package com.pojo.java8.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by sunitc on 4/5/18.
 */
public class ListToMap {

    public static void main(String[] args) {

        List<String> listOne = Arrays.asList("Banana", "Pineapple", "Mango");

        Map<String, Integer> mapOne = listOne.stream().collect(Collectors.toMap(Function.identity(), (f-> f.length()) ));

        System.out.println(mapOne);
    }
}
