package com.play.algorithms_practice.stack_queues;

import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QueueUsingStackTest {

    @Test
    void shouldReturnTrueIfQueueIsEmpty() {
        QueueUsingStack<Character> queue = new QueueUsingStack<>();
        assertThat(queue.isEmpty()).isTrue();

        queue.add('A');
        assertThat(queue.isEmpty()).isFalse();

        queue.remove();
        assertThat(queue.isEmpty()).isTrue();
    }

    @Test
    void shouldBeAbleToAddDataToQueue() {
        QueueUsingStack<Character> queue = new QueueUsingStack<>();

        queue.add('A');
        assertThat(queue.size()).isEqualTo(1);

        queue.add('B');
        assertThat(queue.size()).isEqualTo(2);

        queue.add('C');
        assertThat(queue.size()).isEqualTo(3);
    }


    @Test
    void shouldBeAbleToRemoveElementFromQueueInFIFO() {

        QueueUsingStack<Character> queue = new QueueUsingStack<>();

        queue.add('A');
        queue.add('B');
        queue.add('C');

        assertThat(queue.size()).isEqualTo(3);

        assertThat(queue.remove()).isEqualTo('A');
        assertThat(queue.size()).isEqualTo(2);

        queue.add('D');
        queue.add('E');
        assertThat(queue.size()).isEqualTo(4);

        assertThat(queue.remove()).isEqualTo('B');
        assertThat(queue.size()).isEqualTo(3);

        assertThat(queue.remove()).isEqualTo('C');
        assertThat(queue.size()).isEqualTo(2);

        assertThat(queue.remove()).isEqualTo('D');
        assertThat(queue.size()).isEqualTo(1);

        assertThat(queue.remove()).isEqualTo('E');
        assertThat(queue.isEmpty()).isTrue();

        queue.add('F');
        assertThat(queue.size()).isEqualTo(1);
    }

    @Test
    void shouldThrowExceptionIfRemoveCalledOnEmptyQueue() {

        QueueUsingStack<Character> queue = new QueueUsingStack<>();

        queue.add('A');
        assertThat(queue.remove()).isEqualTo('A');
        assertThat(queue.size()).isEqualTo(0);

        assertThrows(EmptyStackException.class, () -> queue.remove());
    }


    @Test
    void shouldThrowExceptionIfPeekIsCalledOnEmptyQueue() {

        QueueUsingStack<Character> queue = new QueueUsingStack<>();

        assertThrows(EmptyStackException.class, () -> queue.peek());
    }



    @Test
    void shouldBeAbleToCheckFirstElementOfQueueWithoutRemoval() {

        QueueUsingStack<Character> queue = new QueueUsingStack<>();

        queue.add('A');
        queue.add('B');
        queue.add('C');

        assertThat(queue.size()).isEqualTo(3);

        assertThat(queue.peek()).isEqualTo('A');
        assertThat(queue.size()).isEqualTo(3);

        queue.remove();
        queue.add('D');
        queue.add('E');

        assertThat(queue.peek()).isEqualTo('B');

    }

}