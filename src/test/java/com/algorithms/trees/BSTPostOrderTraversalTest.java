package com.algorithms.trees;

import com.data_structures.tree.BSTNode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BSTPostOrderTraversalTest {

    /**
     * Original Tree
     *                      25
     *                /            \
     *             20               35
     *            /  \             /   \
     *          10    24         30     45
     *        /   \             /        \
     *      9     16          27          57
     *     /      /             \
     *    5      12              28
     *            \
     *            15
     *
     *
     */
    BSTNode<Integer> tree = new BSTNode<>(List.of(25, 20, 35, 10, 24, 30, 45, 9, 16, 27, 57, 5, 12, 28, 15));
    List<Integer> expectedElementsInPostOrder = List.of(5, 9, 15, 12, 16, 10, 24, 20, 28, 27, 30, 57, 45, 35, 25);

    BSTPostOrderTraversal<Integer> bstPostOrderTraversal = new BSTPostOrderTraversal<>();

    @Test
    void shouldReturnInOrderSequenceOfNodesUsingRecursion() {
        List<Integer> actualElementsInOrder = bstPostOrderTraversal.postOrderUsingRecursion(tree);
        assertThat(actualElementsInOrder).isEqualTo(expectedElementsInPostOrder);
    }


    @Test
    void shouldReturnInOrderSequenceOfNodesWithoutUsingRecursion() {
        List<Integer> actualElementsInOrder = bstPostOrderTraversal.postOrderWithoutRecursion(tree);
        assertThat(actualElementsInOrder).isEqualTo(expectedElementsInPostOrder);
    }
}