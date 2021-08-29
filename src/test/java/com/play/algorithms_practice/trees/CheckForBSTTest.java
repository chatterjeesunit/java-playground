package com.play.algorithms_practice.trees;

import com.play.data_structures.tree.TreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CheckForBSTTest {

    /**
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
     */
    @Test
    void shouldReturnTrueIfTreeIsBST() {
        TreeNode<Integer> root = new TreeNode<>(25);
        TreeNode<Integer> node20 = root.addLeft(20);
        TreeNode<Integer> node35 = root.addRight(35);
        TreeNode<Integer> node10 = node20.addLeft(10);
        node20.addRight(24);
        TreeNode<Integer> node30 = node35.addLeft(30);
        TreeNode<Integer> node45 = node35.addRight(45);
        TreeNode<Integer> node9 = node10.addLeft(9);
        TreeNode<Integer> node16 = node10.addRight(16);
        node9.addLeft(5);
        TreeNode<Integer> node12 = node16.addLeft(12);
        node12.addRight(15);
        TreeNode<Integer> node27 = node30.addLeft(27);
        node27.addRight(28);
        node45.addRight(57);

        CheckForBST testCls = new CheckForBST();
        assertTrue(testCls.isBST(root));
    }


    /**
     *                  13
     *                /
     *             5
     *            /  \
     *          1    8
     *             /   \
     *            4    12
     *
     */
    @Test
    void shouldReturnFalseIfTreeIsNotABST_TestOne() {
        TreeNode<Integer> root = new TreeNode<>(13);
        TreeNode<Integer> node5 = root.addLeft(5);
        node5.addLeft(1);
        TreeNode<Integer> node8 = node5.addRight(8);
        node8.addLeft(4);
        node8.addRight(12);

        CheckForBST testCls = new CheckForBST();
        assertFalse(testCls.isBST(root));
    }

    /**
     *                  13
     *                /
     *             5
     *            /  \
     *          1    8
     *             /   \
     *            6    26
     *
     */
    @Test
    void shouldReturnFalseIfTreeIsNotABST_TestTwo() {
        TreeNode<Integer> root = new TreeNode<>(13);
        TreeNode<Integer> node5 = root.addLeft(5);
        node5.addLeft(1);
        TreeNode<Integer> node8 = node5.addRight(8);
        node8.addLeft(6);
        node8.addRight(26);

        CheckForBST testCls = new CheckForBST();
        assertFalse(testCls.isBST(root));
    }

}