package com.play.ds_algo.practice.linkedlists;

import com.play.ds_algo.practice.data_structures.linkedlist.LinkedList;
import com.play.ds_algo.practice.data_structures.linkedlist.Node;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class IntersectionTest {

    Intersection<Character> testCls = new Intersection<>();

    @Test
    void shouldReturnIntersectionWhenIntersectingNodeIsInMiddle() {

        LinkedList<Character> firstLinkedList = new LinkedList<>(List.of('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'));

        Node<Character> headOfFirstList = firstLinkedList.getHead();

        Node<Character> nodeG = headOfFirstList.getNext().getNext().getNext().getNext().getNext().getNext();

        Node<Character> headOfSecondList =
                new Node('1',
                        new Node('2',
                                new Node('3', nodeG)));

        Node<Character> intersectingNode = testCls.solve(headOfFirstList, headOfSecondList);

        assertThat(intersectingNode).isNotNull().isSameAs(nodeG);

    }

    @Test
    void shouldReturnIntersectionWhenIntersectingNodeIsTailOfOneList() {

        LinkedList<Character> firstLinkedList = new LinkedList<>(List.of('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'));

        Node<Character> headOfFirstList = firstLinkedList.getHead();

        Node<Character> nodeA = headOfFirstList;

        Node<Character> headOfSecondList =
                new Node('1',
                        new Node('2',
                                new Node('3', nodeA)));

        Node<Character> intersectingNode = testCls.solve(headOfFirstList, headOfSecondList);

        assertThat(intersectingNode).isNotNull().isSameAs(nodeA);
    }

    @Test
    void shouldReturnIntersectionWhenIntersectingNodeHeadOfOneList() {
        LinkedList<Character> firstLinkedList = new LinkedList<>(List.of('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'));

        Node<Character> headOfFirstList = firstLinkedList.getHead();

        Node<Character> nodeJ = firstLinkedList.getTail();

        Node<Character> headOfSecondList =
                new Node('1',
                        new Node('2',
                                new Node('3', nodeJ)));

        Node<Character> intersectingNode = testCls.solve(headOfFirstList, headOfSecondList);

        assertThat(intersectingNode).isNotNull().isSameAs(nodeJ);
    }

    @Test
    void shouldReturnNullIfNoIntersectionFound() {
        LinkedList<Character> firstLinkedList = new LinkedList<>(List.of('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'));
        LinkedList<Character> secondLinkedList = new LinkedList<>(List.of('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'));

        Node<Character> intersectingNode = testCls.solve(firstLinkedList.getHead(), secondLinkedList.getHead());

        assertThat(intersectingNode).isNull();
    }
}