package com.play.data_structures.tree;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TreeNodeTest {

    @Test
    void shouldBeAbleToCreateRootNodeOfTree() {
        TreeNode<Integer> root = new TreeNode<>(5);

        assertThat(root.getData()).isEqualTo(5);
        assertNull(root.getLeft());
        assertNull(root.getRight());
    }


    @Test
    void shouldBeAbleToAddNodeToLeft() {
        TreeNode<Integer> root = new TreeNode<>(5);
        TreeNode<Integer> newNode = root.addLeft(10);

        assertThat(root.getData()).isEqualTo(5);
        assertThat(newNode.getData()).isEqualTo(10);
        assertThat(root.getLeft()).isEqualTo(newNode);
        assertNull(root.getRight());
        assertNull(newNode.getLeft());
        assertNull(newNode.getRight());
    }


    @Test
    void shouldBeAbleToAddNodeToRight() {
        TreeNode<Integer> root = new TreeNode<>(15);
        TreeNode<Integer> newNode = root.addRight(11);

        assertThat(root.getData()).isEqualTo(15);
        assertThat(newNode.getData()).isEqualTo(11);
        assertThat(root.getRight()).isEqualTo(newNode);
        assertNull(root.getLeft());
        assertNull(newNode.getLeft());
        assertNull(newNode.getRight());
    }

}