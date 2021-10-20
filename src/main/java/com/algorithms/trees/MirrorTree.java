package com.algorithms.trees;

import com.data_structures.tree.TreeNode;

/**
 * https://practice.geeksforgeeks.org/problems/mirror-tree/1/
 * Given a Binary Tree, convert it into its mirror.
 *
 * Example 1:
 *
 * Input:
 *       1
 *     /  \
 *    2    3
 * Output: 2 1 3
 *
 * Explanation: The tree is
 *    1    (mirror)  1
 *  /  \    =>      /  \
 * 3    2          2    3
 * The inorder of mirror is 2 1 3
 *
 *
 *
 * Example 2:
 *
 * Input:
 *       10
 *      /  \
 *     20   30
 *    /  \
 *   40  60
 * Output: 30 10 60 20 40
 * Explanation: The tree is
 *       10               10
 *     /    \  (mirror) /    \
 *    20    30    =>   30    20
 *   /  \                   /   \
 *  40  60                 60   40
 * The inorder traversal of mirror is
 * 30 10 60 20 40.
 *
 *
 * Expected Time Complexity: O(N).
 * Expected Auxiliary Space: O(Height of the Tree).
 */
public class MirrorTree<T> {

    TreeNode<T> mirror(TreeNode<T> node) {
        return mirrorRecursively(node);
    }

    TreeNode<T> mirrorRecursively(TreeNode<T> node) {
        if(node == null) return null;
        TreeNode<T> left = node.getLeft();
        TreeNode<T> right = node.getRight();
        node.setLeft(mirrorRecursively(right));
        node.setRight(mirrorRecursively(left));
        return node;
    }
}
