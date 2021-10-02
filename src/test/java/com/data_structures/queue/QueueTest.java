package com.data_structures.queue;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QueueTest {

    @Test
    void shouldReturnTrueIfQueueIsEmpty() {
        Queue<Character> queue = new Queue<>();
        assertThat(queue.isEmpty()).isTrue();

        queue.add('A');
        assertThat(queue.isEmpty()).isFalse();

        queue.remove();
        assertThat(queue.isEmpty()).isTrue();
    }

    @Test
    void shouldBeAbleToAddDataToQueue() {
        Queue<Character> queue = new Queue<>();

        queue.add('A');
        assertThat(queue.size()).isEqualTo(1);

        queue.add('B');
        assertThat(queue.size()).isEqualTo(2);

        queue.add('C');
        assertThat(queue.size()).isEqualTo(3);
    }


    @Test
    void shouldBeAbleToRemoveElementFromQueueInFIFO() {

        Queue<Character> queue = new Queue<>();

        queue.add('A');
        queue.add('B');
        queue.add('C');

        assertThat(queue.size()).isEqualTo(3);

        assertThat(queue.remove()).isEqualTo('A');
        assertThat(queue.size()).isEqualTo(2);

        assertThat(queue.remove()).isEqualTo('B');
        assertThat(queue.size()).isEqualTo(1);

        assertThat(queue.remove()).isEqualTo('C');
        assertThat(queue.size()).isEqualTo(0);
    }

    @Test
    void shouldThrowExceptionIfRemoveCalledOnEmptyQueue() {

        Queue<Character> queue = new Queue<>();

        queue.add('A');
        assertThat(queue.remove()).isEqualTo('A');
        assertThat(queue.size()).isEqualTo(0);

        final RuntimeException exception = assertThrows(RuntimeException.class, () -> queue.remove());

        assertThat(exception.getMessage()).isEqualTo("Queue is Empty");
    }


    @Test
    void shouldThrowExceptionIfPeekIsCalledOnEmptyQueue() {

        Queue<Character> queue = new Queue<>();

        final RuntimeException exception = assertThrows(RuntimeException.class, () -> queue.peek());

        assertThat(exception.getMessage()).isEqualTo("Queue is Empty");
    }



    @Test
    void shouldBeAbleToCheckFirstElementOfQueueWithoutRemoval() {

        Queue<Character> queue = new Queue<>();

        queue.add('A');
        queue.add('B');
        queue.add('C');

        assertThat(queue.size()).isEqualTo(3);

        assertThat(queue.peek()).isEqualTo('A');
        assertThat(queue.size()).isEqualTo(3);
    }

}