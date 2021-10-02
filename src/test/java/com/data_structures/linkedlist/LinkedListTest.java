package com.data_structures.linkedlist;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LinkedListTest {

    @Test
    void shouldBeAbleToCreateALinkedListWithOneElement() {

        LinkedList<Integer> list = new LinkedList<>(1);

        assertThat(list.getHead().getData()).isEqualTo(1);
        assertThat(list.getTail().getData()).isEqualTo(1);
        assertThat(list.getHead()).isSameAs(list.getTail());

    }

    @Test
    void shouldBeAbleToCreateALinkedListWithElements() {

        LinkedList<Integer> list = new LinkedList<>(List.of(1, 2, 3 ,4));

        assertThat(list.getHead().getData()).isEqualTo(1);
        assertThat(list.getHead().getNext().getData()).isEqualTo(2);
        assertThat(list.getHead().getNext().getNext().getData()).isEqualTo(3);
        assertThat(list.getTail().getData()).isEqualTo(4);
    }


    @Test
    void shouldBeAbleToAddElementsToTail() {

        LinkedList<Integer> list = new LinkedList<>(List.of(1, 2));

        list.addNodeToTail(3);
        assertThat(list.getHead().getNext().getNext().getData()).isEqualTo(3);
        assertThat(list.getTail().getData()).isEqualTo(3);

        list.addNodeToTail(4);
        assertThat(list.getHead().getNext().getNext().getData()).isEqualTo(3);
        assertThat(list.getHead().getNext().getNext().getNext().getData()).isEqualTo(4);
        assertThat(list.getTail().getData()).isEqualTo(4);
    }

    @Test
    void shouldBeAbleToAddToTailOfAnEmptyList() {
        LinkedList<Integer> list = new LinkedList<>();

        list.addNodeToTail(1);

        assertThat(list.getHead().getData()).isEqualTo(1);
        assertThat(list.getTail().getData()).isEqualTo(1);
        assertThat(list.getHead()).isSameAs(list.getTail());
    }

    @Test
    void shouldBeAbleToAddElementsToHead() {

        LinkedList<Integer> list = new LinkedList<>(List.of(1, 2));

        list.addNodeToHead(3);
        assertThat(list.getHead().getData()).isEqualTo(3);
        assertThat(list.getHead().getNext().getData()).isEqualTo(1);
        assertThat(list.getTail().getData()).isEqualTo(2);

        list.addNodeToHead(4);
        assertThat(list.getHead().getData()).isEqualTo(4);
        assertThat(list.getHead().getNext().getData()).isEqualTo(3);
        assertThat(list.getHead().getNext().getNext().getData()).isEqualTo(1);
        assertThat(list.getTail().getData()).isEqualTo(2);
    }


    @Test
    void shouldBeAbleToAddToHeadOfAnEmptyList() {
        LinkedList<Integer> list = new LinkedList<>();

        list.addNodeToHead(1);

        assertThat(list.getHead().getData()).isEqualTo(1);
        assertThat(list.getTail().getData()).isEqualTo(1);
        assertThat(list.getHead()).isSameAs(list.getTail());
    }

    @Test
    void shouldBeAbleToReverseAList() {

        LinkedList<Integer> list = new LinkedList<>(List.of(1, 2, 3));

        LinkedList<Integer> reverseList = list.reverse();

        assertThat(reverseList.getHead().getData()).isEqualTo(3);
        assertThat(reverseList.getHead().getNext().getData()).isEqualTo(2);
        assertThat(reverseList.getHead().getNext().getNext().getData()).isEqualTo(1);
        assertThat(reverseList.getTail().getData()).isEqualTo(1);
    }


    @Test
    void shouldReturnEqualityAsTrueIfListElementsAreSame() {

        LinkedList<Integer> list1 = new LinkedList<>(List.of(1, 2, 3));
        LinkedList<Integer> list2 = new LinkedList<>(List.of(1, 2, 3));

        assertThat(list1.equals(list2)).isTrue();
    }


    @Test
    void shouldReturnEqualityAsFalseIfListElementsAreNotSame() {

        LinkedList<Integer> list1 = new LinkedList<>(List.of(1, 2, 3));
        LinkedList<Integer> list2 = new LinkedList<>(List.of(1, 2, 4));

        assertThat(list1.equals(list2)).isFalse();
    }

    @Test
    void shouldReturnEqualityAsFalseIfListAreNotSameSize() {

        LinkedList<Integer> list1 = new LinkedList<>(List.of(1, 2, 3));
        LinkedList<Integer> list2 = new LinkedList<>(List.of(1, 2, 3, 4));

        assertThat(list1.equals(list2)).isFalse();
    }



}