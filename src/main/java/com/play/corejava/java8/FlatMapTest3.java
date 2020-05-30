package com.play.corejava.java8;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlatMapTest3 {

    public static void main(String[] args) {

        List<Parent> parents = Arrays.asList(
                new Parent("A", Arrays.asList(new Dummy("A1"), new Dummy("A2"), new Dummy("A3"))),
                new Parent("B", Arrays.asList(new Dummy("B1"), new Dummy("B2"), new Dummy("B3"))),
                new Parent("C", Arrays.asList(new Dummy("C1"), new Dummy("C2"), new Dummy("C3")))
                );


        List<Dummy> collect = parents.stream().flatMap(p -> p.dummies.stream()).collect(Collectors.toList());

        System.out.println(collect);
    }
}


@Data
@AllArgsConstructor
class Dummy {
    String text;
}

@Data
@AllArgsConstructor
class Parent {
    String id;
    List<Dummy> dummies;
}