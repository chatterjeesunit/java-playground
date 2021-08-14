package com.play.ds_algo.practice.stack_queues;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SortStackTest {

    @Test
    void shouldSortAStackOfNumbers() {
        SortStack<Integer> stack = new SortStack<>(List.of(5, 6, 3, 8, 9, 12, 1, 4, 18, 3, 11, 8, 4, 5));

        stack.sort();

        List<Integer> results = new ArrayList<>();
        while(!stack.isEmpty()) {
            results.add(stack.pop());
        }

        assertThat(results).containsExactly(1, 3, 3, 4, 4, 5, 5, 6, 8, 8, 9, 11, 12 , 18);
    }

    @Test
    void shouldReturnTrueIfStackIsEmpty() {
        SortStack<Character> stack = new SortStack<>(5);
        assertThat(stack.isEmpty()).isTrue();

        stack.push('A');
        assertThat(stack.isEmpty()).isFalse();

        stack.pop();
        assertThat(stack.isEmpty()).isTrue();
    }

    @Test
    void shouldBeAbleToPushDataToStack() {
        SortStack<Character> stack = new SortStack<>(5);

        stack.push('A');
        assertThat(stack.size()).isEqualTo(1);

        stack.push('B');
        assertThat(stack.size()).isEqualTo(2);

        stack.push('C');
        assertThat(stack.size()).isEqualTo(3);
    }


    @Test
    void shouldBeAbleToPopElementFromStackInLIFO() {

        SortStack<Character> stack = new SortStack<>(5);

        stack.push('A');
        stack.push('B');
        stack.push('C');

        assertThat(stack.size()).isEqualTo(3);

        assertThat(stack.pop()).isEqualTo('C');
        assertThat(stack.size()).isEqualTo(2);

        assertThat(stack.pop()).isEqualTo('B');
        assertThat(stack.size()).isEqualTo(1);

        assertThat(stack.pop()).isEqualTo('A');
        assertThat(stack.size()).isEqualTo(0);
    }

    @Test
    void shouldThrowExceptionIfPopCalledOnEmptyStack() {

        SortStack<Character> stack = new SortStack<>(5);

        stack.push('A');
        assertThat(stack.pop()).isEqualTo('A');
        assertThat(stack.size()).isEqualTo(0);

        final RuntimeException exception = assertThrows(RuntimeException.class, () -> stack.pop());

        assertThat(exception.getMessage()).isEqualTo("Stack is Empty");
    }


    @Test
    void shouldThrowExceptionIfPeekIsCalledOnEmptyStack() {

        SortStack<Character> stack = new SortStack<>(5);

        final RuntimeException exception = assertThrows(RuntimeException.class, () -> stack.peek());

        assertThat(exception.getMessage()).isEqualTo("Stack is Empty");
    }



    @Test
    void shouldBeAbleToCheckTopElementOfStackWithoutRemoval() {

        SortStack<Character> stack = new SortStack<>(5);

        stack.push('A');
        stack.push('B');
        stack.push('C');

        assertThat(stack.size()).isEqualTo(3);

        assertThat(stack.peek()).isEqualTo('C');
        assertThat(stack.size()).isEqualTo(3);

    }


}