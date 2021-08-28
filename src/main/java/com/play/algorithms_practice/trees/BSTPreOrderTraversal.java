package com.play.algorithms_practice.trees;

import com.play.data_structures.tree.BSTNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Pre order traversal for a binary search tree
 */
public class BSTPreOrderTraversal<T extends Comparable<T>> {

    public List<T> preOrderUsingRecursion(BSTNode<T> rootNode) {
        List<T> preOrderElements = new ArrayList<>();
        traverseRecursively(rootNode, preOrderElements);
        return preOrderElements;
    }

    private void traverseRecursively(BSTNode<T> currentNode, List<T> preOrderElements) {
        if(currentNode == null) {
            return;
        }
        preOrderElements.add(currentNode.getData());
        traverseRecursively(currentNode.getLeftChild(), preOrderElements);
        traverseRecursively(currentNode.getRightChild(), preOrderElements);
    }

    public List<T> preOrderWithoutRecursion(BSTNode<T> rootNode) {
        List<T> preOrderElements = new ArrayList<>();
        BSTNode<T> currentNode = rootNode;
        Stack<BSTNode<T>> stack = new Stack<>();

        while(currentNode != null || !stack.isEmpty()) {

            if(currentNode == null && !stack.isEmpty()) {
                currentNode = stack.pop();
            }

            preOrderElements.add(currentNode.getData());
            if(currentNode.getRightChild() != null) {
                stack.push(currentNode.getRightChild());
            }
            currentNode = currentNode.getLeftChild();

        }

        return preOrderElements;
    }
}
