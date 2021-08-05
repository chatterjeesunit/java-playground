package com.play.ds_algo.practice.linkedlists;

import com.play.ds_algo.practice.ds.linkedlist.LinkedList;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RemoveDuplicatesTest {

    @Nested
    class RemoveDuplicatesUsingExtraBufferSpace {

        @Test
        void shouldRemoveDuplicatesFromIntegerList() {

            LinkedList<Integer> input = new LinkedList<>(List.of(3, 5, 6, 18, 15, 12, 4, 6, 18, 8, 8));
            LinkedList<Integer> expectedOutput = new LinkedList<>(List.of(3, 5, 6, 18, 15, 12, 4, 8));


            System.out.println("Initial Linked List:\t" + input);
            System.out.println("Expected Linked List:\t" + expectedOutput);
            RemoveDuplicates<Integer> testCls = new RemoveDuplicates<>();
            LinkedList<Integer> actualResult = testCls.solveWithBuffer(input);

            System.out.println("Actual Linked List:\t\t" + actualResult);

            assertThat(actualResult).isEqualTo(expectedOutput);
        }

        @Test
        void shouldRemoveDuplicatesFromStringList() {

            LinkedList<String> input = new LinkedList<>(List.of("a", "b", "c", "d", "a", "d", "c", "b", "e"));
            LinkedList<String> expectedOutput = new LinkedList<>(List.of("a", "b", "c", "d", "e"));


            System.out.println("Initial Linked List:\t" + input);
            System.out.println("Expected Linked List:\t" + expectedOutput);
            RemoveDuplicates<String> testCls = new RemoveDuplicates<>();
            LinkedList<String> actualResult = testCls.solveWithBuffer(input);

            System.out.println("Actual Linked List:\t\t" + actualResult);

            assertThat(actualResult).isEqualTo(expectedOutput);
        }

        @Test
        void shouldReturnSameLinkedListIfContainsOnlyOneItem() {

            LinkedList<Integer> input = new LinkedList<>(List.of(5));
            LinkedList<Integer> expectedOutput = new LinkedList<>(List.of(5));


            System.out.println("Initial Linked List:\t" + input);
            System.out.println("Expected Linked List:\t" + expectedOutput);
            RemoveDuplicates<Integer> testCls = new RemoveDuplicates<>();
            LinkedList<Integer> actualResult = testCls.solveWithBuffer(input);

            System.out.println("Actual Linked List:\t\t" + actualResult);

            assertThat(actualResult).isEqualTo(expectedOutput);
        }

        @Test
        void shouldReturnOneItemListIfAllElementsAreDuplicateOfFirstElement() {

            LinkedList<Integer> input = new LinkedList<>(List.of(3, 3, 3, 3, 3, 3, 3, 3));
            LinkedList<Integer> expectedOutput = new LinkedList<>(List.of(3));


            System.out.println("Initial Linked List:\t" + input);
            System.out.println("Expected Linked List:\t" + expectedOutput);
            RemoveDuplicates<Integer> testCls = new RemoveDuplicates<>();
            LinkedList<Integer> actualResult = testCls.solveWithBuffer(input);

            System.out.println("Actual Linked List:\t\t" + actualResult);

            assertThat(actualResult).isEqualTo(expectedOutput);
        }

    }

    @Nested
    class RemoveDuplicatesWithoutUsingExtraBufferSpace {

        @Test
        void shouldRemoveDuplicatesFromIntegerList() {

            LinkedList<Integer> input = new LinkedList<>(List.of(3, 5, 6, 18, 15, 12, 4, 6, 18, 8, 8));
            LinkedList<Integer> expectedOutput = new LinkedList<>(List.of(3, 5, 6, 18, 15, 12, 4, 8));


            System.out.println("Initial Linked List:\t" + input);
            System.out.println("Expected Linked List:\t" + expectedOutput);
            RemoveDuplicates<Integer> testCls = new RemoveDuplicates<>();
            LinkedList<Integer> actualResult = testCls.solveWithoutBuffer(input);

            System.out.println("Actual Linked List:\t\t" + actualResult);

            assertThat(actualResult).isEqualTo(expectedOutput);
        }

        @Test
        void shouldRemoveDuplicatesFromStringList() {

            LinkedList<String> input = new LinkedList<>(List.of("a", "b", "c", "d", "a", "d", "c", "b", "e"));
            LinkedList<String> expectedOutput = new LinkedList<>(List.of("a", "b", "c", "d", "e"));


            System.out.println("Initial Linked List:\t" + input);
            System.out.println("Expected Linked List:\t" + expectedOutput);
            RemoveDuplicates<String> testCls = new RemoveDuplicates<>();
            LinkedList<String> actualResult = testCls.solveWithoutBuffer(input);

            System.out.println("Actual Linked List:\t\t" + actualResult);

            assertThat(actualResult).isEqualTo(expectedOutput);
        }

        @Test
        void shouldReturnSameLinkedListIfContainsOnlyOneItem() {

            LinkedList<Integer> input = new LinkedList<>(List.of(5));
            LinkedList<Integer> expectedOutput = new LinkedList<>(List.of(5));


            System.out.println("Initial Linked List:\t" + input);
            System.out.println("Expected Linked List:\t" + expectedOutput);
            RemoveDuplicates<Integer> testCls = new RemoveDuplicates<>();
            LinkedList<Integer> actualResult = testCls.solveWithoutBuffer(input);

            System.out.println("Actual Linked List:\t\t" + actualResult);

            assertThat(actualResult).isEqualTo(expectedOutput);
        }

        @Test
        void shouldReturnOneItemListIfAllElementsAreDuplicateOfFirstElement() {

            LinkedList<Integer> input = new LinkedList<>(List.of(3, 3, 3, 3, 3, 3, 3, 3));
            LinkedList<Integer> expectedOutput = new LinkedList<>(List.of(3));


            System.out.println("Initial Linked List:\t" + input);
            System.out.println("Expected Linked List:\t" + expectedOutput);
            RemoveDuplicates<Integer> testCls = new RemoveDuplicates<>();
            LinkedList<Integer> actualResult = testCls.solveWithoutBuffer(input);

            System.out.println("Actual Linked List:\t\t" + actualResult);

            assertThat(actualResult).isEqualTo(expectedOutput);
        }
    }


}