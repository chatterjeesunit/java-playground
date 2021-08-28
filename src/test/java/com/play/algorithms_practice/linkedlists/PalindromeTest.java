package com.play.algorithms_practice.linkedlists;

import com.play.data_structures.linkedlist.LinkedList;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PalindromeTest {

    Palindrome<Character> testCls = new Palindrome<>();

    @Nested
    class TestUsingReversal {

        @Test
        void shouldReturnFalseIfLinkedListIsEmpty() {

            LinkedList<Character> input = new LinkedList<>();
            boolean result = testCls.checkIfPalindromeUsingListReversal(input);
            assertThat(result).isFalse();
        }

        @Test
        void shouldReturnTrueIfLinkedListHasJustOneElement() {

            LinkedList<Character> input = new LinkedList<>('A');
            boolean result = testCls.checkIfPalindromeUsingListReversal(input);
            assertThat(result).isTrue();
        }

        @Test
        void shouldReturnTrueIfLinkedListIsAnEvenPalindrome() {

            LinkedList<Character> input = new LinkedList<>(List.of('a', 'b', 'c', 'd', 'e', 'f', 'f', 'e', 'd', 'c', 'b', 'a'));
            System.out.println("Input:\t" + input);

            boolean result = testCls.checkIfPalindromeUsingListReversal(input);

            assertThat(result).isTrue();
        }

        @Test
        void shouldReturnTrueIfLinkedListIsAnOddPalindrome() {

            LinkedList<Character> input = new LinkedList<>(List.of('a', 'b', 'c', 'd', 'e', 'd', 'c', 'b', 'a'));
            System.out.println("Input:\t" + input);

            boolean result = testCls.checkIfPalindromeUsingListReversal(input);

            assertThat(result).isTrue();
        }

        @Test
        void shouldReturnFalseIfLinkedListIsNotAnPalindrome() {

            LinkedList<Character> input = new LinkedList<>(List.of('a', 'b', 'c', 'd', 'e', 'e', 'c', 'b', 'a'));
            System.out.println("Input:\t" + input);

            boolean result = testCls.checkIfPalindromeUsingListReversal(input);

            assertThat(result).isFalse();
        }
    }


    @Nested
    class TestUsingStack {

        @Test
        void shouldReturnFalseIfLinkedListIsEmpty() {

            LinkedList<Character> input = new LinkedList<>();
            boolean result = testCls.checkIfPalindromeUsingStack(input);
            assertThat(result).isFalse();
        }

        @Test
        void shouldReturnTrueIfLinkedListHasJustOneElement() {

            LinkedList<Character> input = new LinkedList<>('A');
            boolean result = testCls.checkIfPalindromeUsingStack(input);
            assertThat(result).isTrue();
        }


        @Test
        void shouldReturnTrueIfLinkedListIsAnEvenPalindrome() {

            LinkedList<Character> input = new LinkedList<>(List.of('a', 'b', 'c', 'd', 'e', 'f', 'f', 'e', 'd', 'c', 'b', 'a'));
            System.out.println("Input:\t" + input);

            boolean result = testCls.checkIfPalindromeUsingStack(input);

            assertThat(result).isTrue();
        }

        @Test
        void shouldReturnTrueIfLinkedListIsAnOddPalindrome() {

            LinkedList<Character> input = new LinkedList<>(List.of('a', 'b', 'c', 'd', 'e', 'd', 'c', 'b', 'a'));
            System.out.println("Input:\t" + input);

            boolean result = testCls.checkIfPalindromeUsingStack(input);

            assertThat(result).isTrue();
        }

        @Test
        void shouldReturnFalseIfLinkedListIsNotAnPalindrome() {

            LinkedList<Character> input = new LinkedList<>(List.of('a', 'b', 'c', 'd', 'e', 'e', 'c', 'b', 'a'));
            System.out.println("Input:\t" + input);

            boolean result = testCls.checkIfPalindromeUsingStack(input);

            assertThat(result).isFalse();
        }
    }


    @Nested
    class TestUsingRecursion {

        @Test
        void shouldReturnFalseIfLinkedListIsEmpty() {

            LinkedList<Character> input = new LinkedList<>();
            boolean result = testCls.checkIfPalindromeUsingRecursion(input);
            assertThat(result).isFalse();
        }

        @Test
        void shouldReturnTrueIfLinkedListHasJustOneElement() {

            LinkedList<Character> input = new LinkedList<>('A');
            boolean result = testCls.checkIfPalindromeUsingRecursion(input);
            assertThat(result).isTrue();
        }

        @Test
        void shouldReturnTrueIfLinkedListIsAnEvenPalindrome() {

            LinkedList<Character> input = new LinkedList<>(List.of('a', 'b', 'c', 'd', 'e', 'f', 'f', 'e', 'd', 'c', 'b', 'a'));
            System.out.println("Input:\t" + input);

            boolean result = testCls.checkIfPalindromeUsingRecursion(input);

            assertThat(result).isTrue();
        }

        @Test
        void shouldReturnTrueIfLinkedListIsAnOddPalindrome() {

            LinkedList<Character> input = new LinkedList<>(List.of('a', 'b', 'c', 'd', 'e', 'd', 'c', 'b', 'a'));
            System.out.println("Input:\t" + input);

            boolean result = testCls.checkIfPalindromeUsingRecursion(input);

            assertThat(result).isTrue();
        }

        @Test
        void shouldReturnFalseIfLinkedListIsNotAnPalindrome() {

            LinkedList<Character> input = new LinkedList<>(List.of('a', 'b', 'c', 'd', 'e', 'e', 'c', 'b', 'a'));
            System.out.println("Input:\t" + input);

            boolean result = testCls.checkIfPalindromeUsingRecursion(input);

            assertThat(result).isFalse();
        }
    }


}
