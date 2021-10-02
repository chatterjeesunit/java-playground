package com.algorithms.trees;

import com.data_structures.tree.TreeNode;

import java.util.Optional;

/**
 *
 * https://practice.geeksforgeeks.org/contest/interview-series-samsung/problems/#
 *
 * Given a Binary Tree of size N , where each node can have positive or negative
 * values. Convert this to a tree where each node contains the sum of the left and right
 * sub trees of the original tree. The values of leaf nodes are changed to 0.
 *
 * Example 1:
 * Input:
 *         10
 *       /     \
 *     -2        6
 *    /   \     /  \
 *   8    -4   7    5
 * Output:
 *         20
 *       /     \
 *     4        12
 *    /   \     /  \
 *   0    0    0    0
 *
 *
 *   Example 2:
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
public class SumTree {

    public TreeNode<Integer> solve(TreeNode<Integer> root){
        return sumRecursively(root);
    }

    private TreeNode<Integer> sumRecursively(TreeNode<Integer> node) {
        if(node == null) return null;

        int leftNodeValue = getNodeValue(node.getLeft());
        int rightNodeValue = getNodeValue(node.getRight());
        TreeNode<Integer> nodeLeft = sumRecursively(node.getLeft());
        TreeNode<Integer> nodeRight = sumRecursively(node.getRight());

        int sum = leftNodeValue +
                rightNodeValue +
                getNodeValue(nodeLeft) +
                getNodeValue(nodeRight);

        return new TreeNode<>(sum, nodeLeft, nodeRight);
    }

    private int getNodeValue(TreeNode<Integer> node) {
        return Optional.ofNullable(node).map(n -> (Integer)n.getData()).orElse(0);
    }
}
