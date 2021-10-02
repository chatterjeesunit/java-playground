package com.algorithms.linkedlists;

import com.data_structures.linkedlist.LinkedList;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SumListsTest {

    SumLists testCls = new SumLists();

    @Nested
    class ReverseSum {

        @Test
        void shouldReturnSumOfNumbersWithSameDigits(){
            LinkedList<Integer> num1 = new LinkedList<>(List.of(7, 1, 6)); //617
            LinkedList<Integer> num2 = new LinkedList<>(List.of(5, 9, 2)); //295
            LinkedList<Integer> expectedOutput = new LinkedList<>(List.of(2, 1, 9)); //912

            System.out.println("Input Number 1: \t" + num1);
            System.out.println("Input Number 2: \t" + num2);
            System.out.println("Expected Output: \t" + expectedOutput);

            LinkedList<Integer> actualOutput = testCls.solveReverseSum(num1, num2);

            System.out.println("Actual Output: \t\t" + actualOutput);

            assertThat(actualOutput).isEqualTo(expectedOutput);
        }

        @Test
        void shouldReturnSumOfNumbersWithDifferentDigits() {

            LinkedList<Integer> num1 = new LinkedList<>(List.of(7, 1, 6)); //617
            LinkedList<Integer> num2 = new LinkedList<>(List.of(5, 9)); //95
            LinkedList<Integer> expectedOutput = new LinkedList<>(List.of(2, 1, 7)); //712

            System.out.println("Input Number 1: \t" + num1);
            System.out.println("Input Number 2: \t" + num2);
            System.out.println("Expected Output: \t" + expectedOutput);

            LinkedList<Integer> actualOutput = testCls.solveReverseSum(num1, num2);

            System.out.println("Actual Output: \t\t" + actualOutput);

            assertThat(actualOutput).isEqualTo(expectedOutput);

        }

        @Test
        void shouldReturnSumOfNumbersWhenSumResultsInIncreaseOfNumberOfDigits() {

            LinkedList<Integer> num1 = new LinkedList<>(List.of(9, 7, 8)); //879
            LinkedList<Integer> num2 = new LinkedList<>(List.of(6, 8, 5)); //586
            LinkedList<Integer> expectedOutput = new LinkedList<>(List.of(5, 6, 4, 1)); //1466

            System.out.println("Input Number 1: \t" + num1);
            System.out.println("Input Number 2: \t" + num2);
            System.out.println("Expected Output: \t" + expectedOutput);

            LinkedList<Integer> actualOutput = testCls.solveReverseSum(num1, num2);

            System.out.println("Actual Output: \t\t" + actualOutput);

            assertThat(actualOutput).isEqualTo(expectedOutput);

        }
    }


    @Nested
    class OrderedSum {

        @Test
        void shouldReturnSumOfNumbersWithSameDigits(){
            LinkedList<Integer> num1 = new LinkedList<>(List.of(6, 1, 7)); //617
            LinkedList<Integer> num2 = new LinkedList<>(List.of(2, 9, 5)); //295
            LinkedList<Integer> expectedOutput = new LinkedList<>(List.of(9, 1, 2)); //912

            System.out.println("Input Number 1: \t" + num1);
            System.out.println("Input Number 2: \t" + num2);
            System.out.println("Expected Output: \t" + expectedOutput);

            LinkedList<Integer> actualOutput = testCls.orderedSum(num1, num2);

            System.out.println("Actual Output: \t\t" + actualOutput);

            assertThat(actualOutput).isEqualTo(expectedOutput);
        }

        @Test
        void shouldReturnSumOfNumbersWithDifferentDigits() {

            LinkedList<Integer> num1 = new LinkedList<>(List.of(6, 1, 7)); //617
            LinkedList<Integer> num2 = new LinkedList<>(List.of(9, 5)); //95
            LinkedList<Integer> expectedOutput = new LinkedList<>(List.of(7, 1, 2)); //712

            System.out.println("Input Number 1: \t" + num1);
            System.out.println("Input Number 2: \t" + num2);
            System.out.println("Expected Output: \t" + expectedOutput);

            LinkedList<Integer> actualOutput = testCls.orderedSum(num1, num2);

            System.out.println("Actual Output: \t\t" + actualOutput);

            assertThat(actualOutput).isEqualTo(expectedOutput);

        }

        @Test
        void shouldReturnSumOfNumbersWhenSumResultsInIncreaseOfNumberOfDigits() {

            LinkedList<Integer> num1 = new LinkedList<>(List.of(8, 7 , 9)); //879
            LinkedList<Integer> num2 = new LinkedList<>(List.of(5, 8, 6)); //586
            LinkedList<Integer> expectedOutput = new LinkedList<>(List.of(1, 4, 6, 5)); //1466

            System.out.println("Input Number 1: \t" + num1);
            System.out.println("Input Number 2: \t" + num2);
            System.out.println("Expected Output: \t" + expectedOutput);

            LinkedList<Integer> actualOutput = testCls.orderedSum(num1, num2);

            System.out.println("Actual Output: \t\t" + actualOutput);

            assertThat(actualOutput).isEqualTo(expectedOutput);

        }
    }
}