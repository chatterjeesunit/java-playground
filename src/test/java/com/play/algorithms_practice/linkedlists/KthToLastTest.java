package com.play.algorithms_practice.linkedlists;

import com.play.data_structures.linkedlist.LinkedList;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class KthToLastTest {

    KthToLast<Integer> testCls = new KthToLast<>();


    @Test
    void shouldReturnKthToLastElement() {

        LinkedList<Integer> input = new LinkedList<>(List.of(9, 2, 3, 6, 4, 12, 15, 18, 89, 90));
        int n = 5;

        System.out.println("Input:\t" + input);
        System.out.println("K = " + n);

        Integer expectedOutput = 12;
        Integer actualOutput = testCls.solve(input, n);

        assertThat(actualOutput).isEqualTo(expectedOutput);
    }


    @Test
    void shouldReturnHeadElementIfLengthOfListIsK() {

        LinkedList<Integer> input = new LinkedList<>(List.of(9, 2, 3, 6, 4, 12, 15, 18, 89, 90));
        int n = 10;

        System.out.println("Input:\t" + input);
        System.out.println("K = " + n);

        Integer expectedOutput = 9;
        Integer actualOutput = testCls.solve(input, n);

        assertThat(actualOutput).isEqualTo(expectedOutput);
    }


    @Test
    void shouldReturnNullIfLengthOfListIsLessThanK() {

        LinkedList<Integer> input = new LinkedList<>(List.of(9, 2, 3, 6, 4, 12, 15, 18, 89, 90));
        int n = 11;

        System.out.println("Input:\t" + input);
        System.out.println("K = " + n);

        Integer actualOutput = testCls.solve(input, n);

        assertThat(actualOutput).isNull();
    }

}