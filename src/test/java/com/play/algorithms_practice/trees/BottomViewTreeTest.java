package com.play.algorithms_practice.trees;

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
        Node root = new Node(20);
        Node node8 = root.addLeft(8);
        Node node22 = root.addRight(22);
        node8.addLeft(5);
        Node node3 = node8.addRight(3);
        node3.addLeft(10);
        Node node4 = node22.addLeft(4);
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
        Node root = new Node(20);
        Node node8 = root.addLeft(8);
        Node node22 = root.addRight(22);
        node8.addLeft(5);
        Node node3 = node8.addRight(3);
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
        Node root = new Node(1);
        Node node2 = root.addLeft(2);
        Node node3 = root.addRight(3);
        node3.addRight(6);
        Node node4 = node2.addRight(4);
        Node node5 = node4.addRight(5);
        node5.addLeft(7);


        ArrayList<Integer> result = testCls.bottomView(root);

        assertThat(result).isEqualTo(List.of(2, 7, 5, 6));

    }

}