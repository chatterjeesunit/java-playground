package com.play.ds_algo.practice.data_structures.tree;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BSTNodeTest {

    @Test
    void shouldBeAbleToCreateRootNodeOfTree() {
        BSTNode<Integer> rootNode = new BSTNode<>(5);

        assertThat(rootNode.getData()).isEqualTo(5);
        assertThat(rootNode.getLeftChild()).isNull();
        assertThat(rootNode.getRightChild()).isNull();
    }


    @Test
    void shouldBeAbleToAddToBinarySearchTree() {
        BSTNode<Integer> rootNode = new BSTNode<>(5);
        rootNode.addNode(10);
        rootNode.addNode(15);
        rootNode.addNode(12);
        rootNode.addNode(4);
        rootNode.addNode(9);
        rootNode.addNode(1);

        assertThat(rootNode.getData()).isEqualTo(5);

        BSTNode<Integer> node4 = rootNode.getLeftChild();
        BSTNode<Integer> node10 = rootNode.getRightChild();
        assertThat(node4.getData()).isEqualTo(4);
        assertThat(node10.getData()).isEqualTo(10);


        BSTNode<Integer> node1 = node4.getLeftChild();
        BSTNode<Integer> node9 = node10.getLeftChild();
        BSTNode<Integer> node15 = node10.getRightChild();

        assertThat(node1.getData()).isEqualTo(1);
        assertThat(node4.getRightChild()).isNull();
        assertThat(node9.getData()).isEqualTo(9);
        assertThat(node15.getData()).isEqualTo(15);


        BSTNode<Integer> node12 = node15.getLeftChild();
        assertThat(node1.getLeftChild()).isNull();
        assertThat(node1.getRightChild()).isNull();
        assertThat(node9.getLeftChild()).isNull();
        assertThat(node9.getRightChild()).isNull();
        assertThat(node15.getRightChild()).isNull();
        assertThat(node12.getData()).isEqualTo(12);

        assertThat(node12.getLeftChild()).isNull();
        assertThat(node12.getRightChild()).isNull();
    }

}