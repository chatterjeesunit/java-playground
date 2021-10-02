package com.algorithms.trees;

import com.data_structures.tree.TreeNode;

import java.util.*;

/**
 * https://practice.geeksforgeeks.org/problems/bottom-view-of-binary-tree/1#
 *
 *
 * Given a binary tree, print the bottom view from left to right.
 * A node is included in bottom view if it can be seen when we look at the tree from bottom.
 *
 *                       20
 *                     /    \
 *                   8       22
 *                 /   \        \
 *               5      3       25
 *                     /   \
 *                   10    14
 *
 * For the above tree, the bottom view is 5 10 3 14 25.
 * If there are multiple bottom-most nodes for a horizontal distance from root, then print the later one in level traversal.
 * For example, in the below diagram, 3 and 4 are both the bottommost nodes at horizontal distance 0, we need to print 4.
 *
 *                       20
 *                     /    \
 *                   8       22
 *                 /   \     /   \
 *               5      3 4     25
 *                      /    \
 *                  10       14
 *
 * For the above tree the output should be 5 10 4 14 25.
 */
public class BottomViewTree {

    class NodeData {
        TreeNode<Integer> node;
        int distance;

        public NodeData(TreeNode<Integer> node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        public TreeNode<Integer> getNode() {
            return node;
        }

        public int getDistance() {
            return distance;
        }
    }

    public ArrayList<Integer> bottomView(TreeNode<Integer> root)
    {
        Queue<NodeData> queue = new ArrayDeque<>();
        queue.add(new NodeData(root, 0));
        Map<Integer, Integer> bottomViewMap = new TreeMap<>();

        while(!queue.isEmpty()) {
            NodeData nodeData = queue.remove();
            bottomViewMap.put(nodeData.getDistance(), nodeData.getNode().getData());

            if(nodeData.getNode().getLeft() != null) {
                queue.add(new NodeData(nodeData.getNode().getLeft(), nodeData.getDistance() - 1));
            }

            if(nodeData.getNode().getRight() != null) {
                queue.add(new NodeData(nodeData.getNode().getRight(), nodeData.getDistance() + 1));
            }
        }

        return new ArrayList<>(bottomViewMap.values());
    }
}