package com.play.ds_algo.practice.data_structures.stack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    @Test
    void shouldBeAbleToPushDataToStack() {
        Stack<Character> stack = new Stack<>(5);

        stack.push('A');
        assertThat(stack.length()).isEqualTo(1);

        stack.push('B');
        assertThat(stack.length()).isEqualTo(2);

        stack.push('C');
        assertThat(stack.length()).isEqualTo(3);
    }

    @Test
    void shouldBeAbleToPushDataToStackEvenIfStackIsCurrentlyFull() {

        Stack<Character> stack = new Stack<>(2);

        stack.push('A');
        assertThat(stack.length()).isEqualTo(1);
        assertThat(stack.getCapacity()).isEqualTo(2);

        stack.push('B');
        assertThat(stack.length()).isEqualTo(2);
        assertThat(stack.getCapacity()).isEqualTo(2);

        stack.push('C');
        assertThat(stack.length()).isEqualTo(3);
        assertThat(stack.getCapacity()).isGreaterThan(2);
    }


    @Test
    void shouldBeAbleToPopElementFromStack() {

        Stack<Character> stack = new Stack<>(5);

        stack.push('A');
        stack.push('B');
        stack.push('C');

        assertThat(stack.length()).isEqualTo(3);

        assertThat(stack.pop()).isEqualTo('C');
        assertThat(stack.length()).isEqualTo(2);

        assertThat(stack.pop()).isEqualTo('B');
        assertThat(stack.length()).isEqualTo(1);

        assertThat(stack.pop()).isEqualTo('A');
        assertThat(stack.length()).isEqualTo(0);
    }

    @Test
    void shouldThrowExceptionIfPopCalledOnEmptyStack() {

        Stack<Character> stack = new Stack<>(5);

        stack.push('A');
        assertThat(stack.pop()).isEqualTo('A');
        assertThat(stack.length()).isEqualTo(0);

        final RuntimeException exception = assertThrows(RuntimeException.class, () -> stack.pop());

        assertThat(exception.getMessage()).isEqualTo("Stack is Empty");
    }


    @Test
    void shouldThrowExceptionIfPeekIsCalledOnEmptyStack() {

        Stack<Character> stack = new Stack<>(5);

        final RuntimeException exception = assertThrows(RuntimeException.class, () -> stack.peek());

        assertThat(exception.getMessage()).isEqualTo("Stack is Empty");
    }



    @Test
    void shouldBeAbleToCheckTopElementOfStackWithoutRemoval() {

        Stack<Character> stack = new Stack<>(5);

        stack.push('A');
        stack.push('B');
        stack.push('C');

        assertThat(stack.length()).isEqualTo(3);

        assertThat(stack.peek()).isEqualTo('C');
        assertThat(stack.length()).isEqualTo(3);

        assertThat(stack.peek()).isEqualTo('C');
        assertThat(stack.length()).isEqualTo(3);

    }
}
