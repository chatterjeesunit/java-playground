package com.play.ds_algo.practice.linkedlists;

import com.play.ds_algo.practice.data_structures.linkedlist.LinkedList;
import com.play.ds_algo.practice.data_structures.linkedlist.Node;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteMiddleNodeTest {

    DeleteMiddleNode<String> testCls = new DeleteMiddleNode<>();


    @Test
    void shouldDeleteTheMiddleNode() {
        LinkedList<String> linkedList = new LinkedList<>(List.of("a", "b", "c", "d", "e", "f"));
        Node<String> input = linkedList.getHead().getNext().getNext();

        LinkedList<String> expectedLinkedList = new LinkedList<>(List.of("a", "b", "d", "e", "f"));

        testCls.solve(input);

        assertThat(linkedList).isEqualTo(expectedLinkedList);
    }

    @Test
    void shouldDeleteTheSecondLastNode() {
        LinkedList<String> linkedList = new LinkedList<>(List.of("a", "b", "c", "d"));
        Node<String> input = linkedList.getHead().getNext().getNext();

        LinkedList<String> expectedLinkedList = new LinkedList<>(List.of("a", "b", "d"));

        testCls.solve(input);

        assertThat(linkedList).isEqualTo(expectedLinkedList);
    }

    @Test
    void shouldThrowErrorIfNodeToBeDeletedIsLastNode() {

        LinkedList<String> linkedList = new LinkedList<>(List.of("a", "b", "c"));
        Node<String> input = linkedList.getHead().getNext().getNext();


        Exception exception = assertThrows(UnsupportedOperationException.class, () -> testCls.solve(input));

        assertThat(exception.getMessage()).isEqualTo("Cannot delete the last node.");
    }
}