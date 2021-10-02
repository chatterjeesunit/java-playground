package com.corejava.generics;

import java.util.Set;
import java.util.stream.Collectors;

public class ArithmeticOperation<N extends Number> {

    public Set<N> findCubeElementInList(Set<N> inputList) {

        Set<Double> listWithDoubles =
                inputList.stream().map(num -> num.doubleValue()).collect(Collectors.toSet());

        return inputList
                .stream()
                .filter(number -> {
                    double cubeVal = Math.pow(number.doubleValue(), 3.0);
                    return listWithDoubles.contains(cubeVal);
                })
                .collect(Collectors.toSet());

    }
}
