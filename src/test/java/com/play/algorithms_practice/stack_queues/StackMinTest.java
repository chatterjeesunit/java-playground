package com.play.algorithms_practice.stack_queues;

import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StackMinTest {

    @Test
    void shouldThrowEmptyStackExceptionWhenPeekIsDoneOnEmptyStack() {
        StackMin<Integer> stack = new StackMin<>();
        assertThrows(EmptyStackException.class, () -> stack.peek());
    }

    @Test
    void shouldThrowEmptyStackExceptionWhenPopIsDoneOnEmptyStack() {
        StackMin<Integer> stack = new StackMin<>();
        assertThrows(EmptyStackException.class, () -> stack.pop());
    }

    @Test
    void shouldThrowEmptyStackExceptionWhenMinElementIsFetchedFromEmptyStack() {
        StackMin<Integer> stack = new StackMin<>();
        assertThrows(EmptyStackException.class, () -> stack.min());
    }

    @Test
    void shouldReturnTrueWhenStackIsEmpty() {
        StackMin<Integer> stack = new StackMin<>();
        assertThat(stack.isEmpty()).isTrue();
    }

    @Test
    void shouldReturnFalseWhenStackIsNotEmpty() {
        StackMin<Integer> stack = new StackMin<>();
        stack.push(1);
        assertThat(stack.isEmpty()).isFalse();
    }

    @Test
    void shouldBeAbleToPushElementsToStackAndFetchMinElements() {

        StackMin<Integer> stack = new StackMin<>();

        stack.push(10);
        assertThat(stack.size()).isEqualTo(1);
        assertThat(stack.peek()).isEqualTo(10);
        assertThat(stack.min()).isEqualTo(10);


        stack.push(15);
        assertThat(stack.size()).isEqualTo(2);
        assertThat(stack.peek()).isEqualTo(15);
        assertThat(stack.min()).isEqualTo(10);


        stack.push(11);
        assertThat(stack.size()).isEqualTo(3);
        assertThat(stack.peek()).isEqualTo(11);
        assertThat(stack.min()).isEqualTo(10);

        stack.push(5);
        assertThat(stack.size()).isEqualTo(4);
        assertThat(stack.peek()).isEqualTo(5);
        assertThat(stack.min()).isEqualTo(5);

    }

    @Test
    void shouldBeAbleToPushAndPopElementsToStackAndFetchMinElements() {

        StackMin<Integer> stack = new StackMin<>();

        stack.push(10);
        assertThat(stack.size()).isEqualTo(1);
        assertThat(stack.peek()).isEqualTo(10);
        assertThat(stack.min()).isEqualTo(10);


        stack.push(9);
        assertThat(stack.size()).isEqualTo(2);
        assertThat(stack.peek()).isEqualTo(9);
        assertThat(stack.min()).isEqualTo(9);


        stack.push(15);
        assertThat(stack.size()).isEqualTo(3);
        assertThat(stack.peek()).isEqualTo(15);
        assertThat(stack.min()).isEqualTo(9);

        stack.push(5);
        assertThat(stack.size()).isEqualTo(4);
        assertThat(stack.peek()).isEqualTo(5);
        assertThat(stack.min()).isEqualTo(5);

        assertThat(stack.pop()).isEqualTo(5);
        assertThat(stack.min()).isEqualTo(9);


        assertThat(stack.pop()).isEqualTo(15);
        assertThat(stack.min()).isEqualTo(9);

        assertThat(stack.pop()).isEqualTo(9);
        assertThat(stack.min()).isEqualTo(10);

    }

    
}