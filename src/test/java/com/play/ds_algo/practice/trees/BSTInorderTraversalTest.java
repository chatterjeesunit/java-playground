package com.play.ds_algo.practice.trees;

import com.play.ds_algo.practice.data_structures.tree.BSTNode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BSTInorderTraversalTest {


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
    List<Integer> expectedElementsInOrder = List.of(5, 9, 10, 12, 15, 16, 20, 24, 25, 27, 28, 30, 35, 45, 57);
    BSTInorderTraversal<Integer> bstInorderTraversal = new BSTInorderTraversal<>();

    @Test
    void shouldReturnInOrderSequenceOfNodesUsingRecursion() {
        List<Integer> actualElementsInOrder = bstInorderTraversal.inOrderUsingRecursion(tree);
        assertThat(actualElementsInOrder).isEqualTo(expectedElementsInOrder);
    }


    @Test
    void shouldReturnInOrderSequenceOfNodesWithoutUsingRecursion() {
        List<Integer> actualElementsInOrder = bstInorderTraversal.inOrderWithoutRecursion(tree);
        assertThat(actualElementsInOrder).isEqualTo(expectedElementsInOrder);
    }

}