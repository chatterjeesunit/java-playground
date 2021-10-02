package com.algorithms.trees;

import com.data_structures.tree.BSTNode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BSTreeEqualityTest {

    @Test
    void shouldReturnTrueForIfOneNodeTreeAreEqual() {
        BSTNode<Integer> tree1 = new BSTNode<>(5);
        BSTNode<Integer> tree2 = new BSTNode<>(5);

        boolean areTreeSame = BSTreeEquality.isEqual(tree1, tree2);
        assertThat(areTreeSame).isTrue();

    }

    @Test
    void shouldReturnFalseForIfRootNodeAreNotSame() {
        BSTNode<Integer> tree1 = new BSTNode<>(5);
        BSTNode<Integer> tree2 = new BSTNode<>(4);

        boolean areTreeSame = BSTreeEquality.isEqual(tree1, tree2);

        assertThat(areTreeSame).isFalse();

    }

    @Test
    void shouldReturnFalseForIfTreeAreOfDifferentTypes() {
        BSTNode<Integer> tree1 = new BSTNode<>(Integer.valueOf(5));
        BSTNode<Float> tree2 = new BSTNode<>(Float.valueOf(5));

        boolean areTreeSame = BSTreeEquality.isEqual(tree1, tree2);

        assertThat(areTreeSame).isFalse();

    }


    @Test
    void shouldReturnTrueIfTreesAreActuallySame() {
        BSTNode<Integer> tree1 = new BSTNode<>(List.of(5, 10, 15, 12, 4, 9, 1));
        BSTNode<Integer> tree2 = new BSTNode<>(List.of(5, 10, 15, 4, 9, 12, 1));

        boolean areTreeSame = BSTreeEquality.isEqual(tree1, tree2);
        assertThat(areTreeSame).isTrue();
    }


    @Test
    void shouldReturnTrueIfTreesAreNotSame() {
        BSTNode<Integer> tree1 = new BSTNode<>(List.of(5, 10, 15, 12, 4, 9, 1));
        BSTNode<Integer> tree2 = new BSTNode<>(List.of(5, 10, 12, 4, 9, 1, 15));


        boolean areTreeSame = BSTreeEquality.isEqual(tree1, tree2);
        assertThat(areTreeSame).isFalse();

    }

}