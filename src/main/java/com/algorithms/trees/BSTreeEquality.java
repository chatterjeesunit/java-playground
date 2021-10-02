package com.algorithms.trees;

import com.data_structures.tree.BSTNode;

/**
 * Test if two Binary search tree are equal orn ot
 */
public class BSTreeEquality {


    /**
     * Time Complexity
     *      - We compare nodes of tree one by one till all nodes are compared
     *      - O (2^k) where k is depth of the smallest tree
     *  Space Complexity
     *      - No. of recursions = numbers of nodes - 1
     *      - O (2^k) where k is depth of the smallest tree
     */
    public static boolean isEqual(BSTNode tree1, BSTNode tree2) {
        if(tree1 == null && tree2 == null) {
            return true;
        }

        if((tree1 == null && tree2 != null)
                || (tree1 != null && tree2 == null)) {
            return false;
        }

        boolean areNodeEqual = tree1.getData().equals(tree2.getData());

        if(areNodeEqual) {
            areNodeEqual = isEqual(tree1.getLeftChild(), tree2.getLeftChild());
        }
        if(areNodeEqual) {
            areNodeEqual = isEqual(tree1.getRightChild(), tree2.getRightChild());
        }

        return areNodeEqual;
    }
}
