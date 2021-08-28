package com.play.algorithms_practice.trees;

import com.play.data_structures.tree.BSTNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * InOrder traversal of a binary tree
 */
public class BSTInorderTraversal<T extends Comparable<T>> {

    public List<T> inOrderUsingRecursion(BSTNode<T> rootNode) {
        List<T> inOrderElements = new ArrayList<>();
        traverseRecursively(rootNode, inOrderElements);
        return inOrderElements;
    }

    private void traverseRecursively(BSTNode<T> currentNode, List<T> inOrderElements ) {
        if(currentNode == null) {
            return;
        }
        traverseRecursively(currentNode.getLeftChild(), inOrderElements);
        inOrderElements.add(currentNode.getData());
        traverseRecursively(currentNode.getRightChild(), inOrderElements);
    }


    public List<T> inOrderWithoutRecursion(BSTNode<T> rootNode) {

        Stack<BSTNode<T>> stack = new Stack<>();
        List<T> inOrderElements = new ArrayList<>();

        BSTNode<T> currentNode = rootNode;

        if(rootNode ==  null) return null;

        while(currentNode != null || !stack.isEmpty()) {
            while(currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.getLeftChild();
            }

            currentNode = stack.pop();
            inOrderElements.add(currentNode.getData());

            currentNode = currentNode.getRightChild();
        }

        return inOrderElements;

    }
}
