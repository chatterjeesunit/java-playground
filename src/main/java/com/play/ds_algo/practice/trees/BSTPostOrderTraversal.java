package com.play.ds_algo.practice.trees;

import com.play.ds_algo.practice.data_structures.tree.BSTNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Pre order traversal for a binary search tree
 */
public class BSTPostOrderTraversal<T extends Comparable<T>> {

    public List<T> postOrderUsingRecursion(BSTNode<T> rootNode) {
        List<T> postOrderElements = new ArrayList<>();
        traverseRecursively(rootNode, postOrderElements);
        return postOrderElements;
    }

    private void traverseRecursively(BSTNode<T> currentNode, List<T> postOrderElements) {
        if(currentNode == null) {
            return;
        }
        traverseRecursively(currentNode.getLeftChild(), postOrderElements);
        traverseRecursively(currentNode.getRightChild(), postOrderElements);
        postOrderElements.add(currentNode.getData());
    }

    public List<T> postOrderWithoutRecursion(BSTNode<T> rootNode) {
        List<T> postOrderElements = new ArrayList<>();
        Stack<BSTNode<T>> stack = new Stack<>();
        BSTNode<T> currentNode = rootNode;

        while(currentNode != null || !stack.isEmpty()) {

            while(currentNode != null) {
                if(currentNode.getRightChild() != null) {
                    stack.push(currentNode.getRightChild());
                }
                stack.push(currentNode);
                currentNode = currentNode.getLeftChild();
            }

            BSTNode<T> node1 = stack.pop();
            BSTNode<T> node2 = stack.isEmpty() ? null : stack.peek();

            if(node2 != null && node2.equals(node1.getRightChild())) {
                currentNode = stack.pop();
                stack.push(node1);
            } else {
                postOrderElements.add(node1.getData());
            }
        }

        return postOrderElements;
    }
}
