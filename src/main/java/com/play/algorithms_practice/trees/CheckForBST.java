package com.play.algorithms_practice.trees;

import com.play.data_structures.tree.TreeNode;

import java.util.Optional;

/**
 *
 * https://practice.geeksforgeeks.org/problems/check-for-bst/1/
 *
 * Given a binary tree. Check whether it is a BST or not.
 * Note: We are considering that BSTs can not contain duplicate Nodes.
 *
 * Example 1: Below Tree is a BST
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
 * Example 2: Below Tree is not a BST
 *                  13
 *                /
 *             5
 *            /  \
 *          1    8
 *             /   \
 *            4    12
 *
 *
 * Example 3: Below Tree is not a BST
 *                  13
 *                /
 *             5
 *            /  \
 *          1    8
 *             /   \
 *            6    26
 *
 */
public class CheckForBST {


    boolean isBST(TreeNode<Integer> root)
    {
        return checkRecursively(root, null, null);
    }

    boolean checkRecursively(TreeNode<Integer> node, Integer minLimit, Integer maxLimit) {
        if(node == null) {
            return true;
        }

        boolean isValid = Optional.ofNullable(minLimit).map(val -> node.getData() > val ).orElse(true) &&
                Optional.ofNullable(maxLimit).map(val -> node.getData() < val ).orElse(true);

        if(isValid) {
            isValid = checkRecursively(node.getLeft(), minLimit, node.getData());
        }

        if(isValid) {
            isValid = checkRecursively(node.getRight(), node.getData(), maxLimit);
        }

        return isValid;
    }
}
