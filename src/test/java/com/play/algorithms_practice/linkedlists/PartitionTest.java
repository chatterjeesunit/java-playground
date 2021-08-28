package com.play.algorithms_practice.linkedlists;

import com.play.data_structures.linkedlist.LinkedList;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PartitionTest {

    Partition<Integer> testCls = new Partition<>();


    @Test
    void shouldPartitionTheLinkedList() {

        LinkedList<Integer> input = new LinkedList<>(List.of(3, 5, 8, 5, 10, 2, 1));
        LinkedList<Integer> expectedOutput = new LinkedList<>(List.of(3, 2, 1, 5, 10, 5, 8));

        int partitionElement = 5;

        System.out.println("Input List: \t\t" + input);
        System.out.println("Partition Element: \t" + partitionElement);
        System.out.println("Expected Output: \t" + expectedOutput);

        LinkedList<Integer> actualOutput = testCls.solveBySwap(input, partitionElement);
        System.out.println("Actual Output: \t\t" + actualOutput);

        assertThat(actualOutput).isEqualTo(expectedOutput);
    }

}