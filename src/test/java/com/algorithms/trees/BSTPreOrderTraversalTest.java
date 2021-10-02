package com.algorithms.trees;

import com.data_structures.tree.BSTNode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BSTPreOrderTraversalTest {

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
    List<Integer> expectedElementsInPreOrder = List.of(25, 20, 10, 9, 5, 16, 12, 15, 24, 35, 30, 27, 28, 45, 57);

    BSTPreOrderTraversal<Integer> bstPreOrderTraversal = new BSTPreOrderTraversal<>();

    @Test
    void shouldReturnInOrderSequenceOfNodesUsingRecursion() {
        List<Integer> actualElementsInOrder = bstPreOrderTraversal.preOrderUsingRecursion(tree);
        assertThat(actualElementsInOrder).isEqualTo(expectedElementsInPreOrder);
    }


    @Test
    void shouldReturnInOrderSequenceOfNodesWithoutUsingRecursion() {
        List<Integer> actualElementsInOrder = bstPreOrderTraversal.preOrderWithoutRecursion(tree);
        assertThat(actualElementsInOrder).isEqualTo(expectedElementsInPreOrder);
    }

}