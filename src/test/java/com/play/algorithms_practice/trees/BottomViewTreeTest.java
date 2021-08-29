package com.play.algorithms_practice.trees;

import com.play.data_structures.tree.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BottomViewTreeTest {

    BottomViewTree testCls = new BottomViewTree();

    /**
     *                       20
     *                     /    \
     *                   8       22
     *                 /   \     /   \
     *               5      3 4     25
     *                      /    \
     *                  10       14
     *
     *  Expected result - 5 10 4 14 25
     */
    @Test
    void shouldGetBottomViewOfTreeTestOne() {
        TreeNode<Integer> root = new TreeNode<>(20);
        TreeNode<Integer> node8 = root.addLeft(8);
        TreeNode<Integer> node22 = root.addRight(22);
        node8.addLeft(5);
        TreeNode<Integer> node3 = node8.addRight(3);
        node3.addLeft(10);
        TreeNode<Integer> node4 = node22.addLeft(4);
        node22.addRight(25);
        node4.addRight(14);

        ArrayList<Integer> result = testCls.bottomView(root);

        assertThat(result).isEqualTo(List.of(5, 10, 4, 14, 25));

    }


    /**
     *                       20
     *                     /    \
     *                   8       22
     *                 /   \        \
     *               5      3       25
     *                     /   \
     *                   10    14
     *
     *  Expected result - 5 10 3 14 25
     */
    @Test
    void shouldGetBottomViewOfTreeTestTwo() {
        TreeNode<Integer> root = new TreeNode<>(20);
        TreeNode<Integer> node8 = root.addLeft(8);
        TreeNode<Integer> node22 = root.addRight(22);
        node8.addLeft(5);
        TreeNode<Integer> node3 = node8.addRight(3);
        node3.addLeft(10);
        node3.addRight(14);
        node22.addRight(25);


        ArrayList<Integer> result = testCls.bottomView(root);

        assertThat(result).isEqualTo(List.of(5, 10, 3, 14, 25));

    }


    /**
     *                       1
     *                     /    \
     *                   2       3
     *                      \      \
     *                       4      6
     *                         \
     *                           5
     *                         /
     *                       7
     *
     *  Expected result - 2 7 5 6
     */
    @Test
    void shouldGetBottomViewOfTreeTestThree() {
        TreeNode<Integer> root = new TreeNode<>(1);
        TreeNode<Integer> node2 = root.addLeft(2);
        TreeNode<Integer> node3 = root.addRight(3);
        node3.addRight(6);
        TreeNode<Integer> node4 = node2.addRight(4);
        TreeNode<Integer> node5 = node4.addRight(5);
        node5.addLeft(7);


        ArrayList<Integer> result = testCls.bottomView(root);

        assertThat(result).isEqualTo(List.of(2, 7, 5, 6));

    }

}