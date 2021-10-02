package com.algorithms.trees;

import com.data_structures.tree.TreeNode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class SumTreeTest {

    SumTree testClass = new SumTree();

    /**
     * Input:
     *         10
     *       /     \
     *     -2        6
     *    /   \     /  \
     *   8    -4   7    5
     *
     * Expected Output:
     *         20
     *       /     \
     *     4        12
     *    /   \     /  \
     *   0    0    0    0
     *
     */
    @Test
    void shouldReturnSumOfTree_TestOne() {

        TreeNode<Integer> root = new TreeNode<>(10);
        TreeNode<Integer> node2minus = root.addLeft(-2);
        TreeNode<Integer> node6 = root.addRight(6);
        node2minus.addLeft(8);
        node2minus.addRight(-4);
        node6.addLeft(7);
        node6.addRight(5);


        TreeNode<Integer> resultTree = testClass.solve(root);

        assertThat(resultTree.getData()).isEqualTo(20);
        assertThat(resultTree.getLeft().getData()).isEqualTo(4);
        assertThat(resultTree.getRight().getData()).isEqualTo(12);
        assertThat(resultTree.getLeft().getLeft().getData()).isEqualTo(0);
        assertThat(resultTree.getLeft().getRight().getData()).isEqualTo(0);
        assertThat(resultTree.getRight().getLeft().getData()).isEqualTo(0);
        assertThat(resultTree.getRight().getRight().getData()).isEqualTo(0);
    }


    /**
     *  Input:
     *             10
     *         /        \
     *       -2           6
     *      /   \        /  \
     *     8    -4      7     5
     *     / \   / \    / \   / \
     *   2  -2 3  -5  9  -8 2   8
     * Output:
     *             29
     *         /        \
     *        2          23
     *      /  \        /  \
     *     0   -2      1    10
     *    / \  / \    / \   / \
     *    0  0 0   0  0   0 0   0
     *
     */
    @Test
    void shouldReturnSumOfTree_TestTwo() {

        TreeNode<Integer> root = new TreeNode<>(10);
        TreeNode<Integer> node2minus = root.addLeft(-2);
        TreeNode<Integer> node6 = root.addRight(6);
        TreeNode<Integer> node8 = node2minus.addLeft(8);
        TreeNode<Integer> node4minus = node2minus.addRight(-4);
        TreeNode<Integer> node7 = node6.addLeft(7);
        TreeNode<Integer> node5 = node6.addRight(5);
        node8.addLeft(2);
        node8.addRight(-2);
        node4minus.addLeft(3);
        node4minus.addRight(-5);
        node7.addLeft(9);
        node7.addRight(-8);
        node5.addLeft(2);
        node5.addRight(8);


        TreeNode<Integer> resultTree = testClass.solve(root);

        assertThat(resultTree.getData()).isEqualTo(29);

        assertThat(resultTree.getLeft().getData()).isEqualTo(2);
        assertThat(resultTree.getRight().getData()).isEqualTo(23);

        assertThat(resultTree.getLeft().getLeft().getData()).isEqualTo(0);
        assertThat(resultTree.getLeft().getRight().getData()).isEqualTo(-2);
        assertThat(resultTree.getRight().getLeft().getData()).isEqualTo(1);
        assertThat(resultTree.getRight().getRight().getData()).isEqualTo(10);

        assertThat(resultTree.getLeft().getLeft().getLeft().getData()).isEqualTo(0);
        assertThat(resultTree.getLeft().getLeft().getRight().getData()).isEqualTo(0);
        assertThat(resultTree.getLeft().getRight().getLeft().getData()).isEqualTo(0);
        assertThat(resultTree.getLeft().getRight().getRight().getData()).isEqualTo(0);
        assertThat(resultTree.getRight().getLeft().getLeft().getData()).isEqualTo(0);
        assertThat(resultTree.getRight().getLeft().getRight().getData()).isEqualTo(0);
        assertThat(resultTree.getRight().getRight().getLeft().getData()).isEqualTo(0);
        assertThat(resultTree.getRight().getRight().getRight().getData()).isEqualTo(0);

    }

}