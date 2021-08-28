package com.play.algorithms_practice.linkedlists;

import com.play.data_structures.linkedlist.LinkedList;
import com.play.data_structures.linkedlist.Node;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LoopDetectionTest {

    LoopDetection<Character> testCls = new LoopDetection<>();

    @Test
    void shouldReturnNodeIfLoopExists() {

        LinkedList<Character> list = new LinkedList<>(List.of('A', 'B', 'C', 'D', 'E', 'F'));
        Node<Character> nodeC = list.getHead().getNext().getNext();

        list.getTail().setNext(nodeC);

        Node<Character> loopNode = testCls.findLoopNode(list.getHead());

        assertThat(loopNode).isSameAs(nodeC);
    }

    @Test
    void shouldReturnHeaderNodeIfTailPointsToHeadExists() {

        LinkedList<Character> list = new LinkedList<>(List.of('A', 'B', 'C', 'D', 'E', 'F'));
        list.getTail().setNext(list.getHead());

        Node<Character> loopNode = testCls.findLoopNode(list.getHead());

        assertThat(loopNode).isSameAs(list.getHead());
    }


    @Test
    void shouldReturnTailNodeWhenTailPointsToItself() {

        LinkedList<Character> list = new LinkedList<>(List.of('A', 'B', 'C', 'D', 'E', 'F'));
        list.getTail().setNext(list.getTail());

        Node<Character> loopNode = testCls.findLoopNode(list.getHead());

        assertThat(loopNode).isSameAs(list.getTail());
    }


    @Test
    void shouldReturnNullIfNoLoopExists() {

        LinkedList<Character> list = new LinkedList<>(List.of('A', 'B', 'C', 'D', 'E', 'F'));

        Node<Character> loopNode = testCls.findLoopNode(list.getHead());

        assertThat(loopNode).isNull();
    }

    @Test
    void shouldReturnNullIfListIsEmpty() {

        LinkedList<Character> list = new LinkedList<>();

        Node<Character> loopNode = testCls.findLoopNode(list.getHead());

        assertThat(loopNode).isNull();
    }


    @Test
    void shouldReturnNullIfListIsHasJustOneElement() {

        LinkedList<Character> list = new LinkedList<>();

        Node<Character> loopNode = testCls.findLoopNode(list.getHead());

        assertThat(loopNode).isNull();
    }

}