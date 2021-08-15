package com.play.ds_algo.practice.data_structures.tree;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

/**
 * Binary Search Tree Node
 */
@Getter
@Setter
public class BSTNode<T extends Comparable<T>> implements Comparable<BSTNode<T>> {
    T data;
    BSTNode<T> leftChild;
    BSTNode<T> rightChild;

    public BSTNode(T data) {
        this.data = data;
    }

    /**
     * Insert the elements from the list one by one into the tree
     */
    public BSTNode(List<T> elements) {
       if(elements == null || elements.isEmpty()) {
           throw new IllegalArgumentException();
       }
       this.data = elements.get(0);
        for (int i = 1; i < elements.size(); i++) {
            this.addNode(elements.get(i));
        }
    }

    @Override
    public int compareTo(BSTNode<T> other) {
        if(other == null) {
            throw new NullPointerException();
        }
        return this.getData().compareTo(other.getData());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BSTNode<?> bstNode = (BSTNode<?>) o;
        return data.equals(bstNode.data) && Objects.equals(leftChild, bstNode.leftChild) && Objects.equals(rightChild, bstNode.rightChild);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, leftChild, rightChild);
    }



    /**
     * Time Complexity : O (log n)
     *  for a tree with k levels and at max n nodes, where n = 2^k - 1
     * Space Complexity : O (log n) - at max the number of recursions will be equal to depth of tree.
     *  and each recursion step will need to store data.
     *
     */
    public BSTNode<T> addNode(T data) {
        return addNodeRecursively(this, data);
    }


    /**
     * Time Complexity :
     *      - Finding node to delete = O (k) , where k is the depth where node is to be deleted
     *      - find a replacement node for deleted node = O (log n - k), where k is current depth of tree
     *  So time complexity is O (log n)
     *
     * Space Complexity : O (log n) - at max the number of recursions will be equal to depth of tree.
     *  and each recursion step will need to store data.
     *
     */
    public BSTNode<T> deleteNode(T data) {
        return deleteNodeRecursively(this, data);
    }

    private BSTNode<T> deleteNodeRecursively(BSTNode<T> currentNode, T data) {
        if(currentNode == null) {
            return null;
        }

        int comparison = data.compareTo(currentNode.getData());

        if (comparison == 0) {
            //Node found. Delete it
            currentNode = deleteNode(currentNode);
        } else if(comparison < 0) {
            BSTNode<T> leftTree = deleteNodeRecursively(currentNode.getLeftChild(), data);
            currentNode.setLeftChild(leftTree);
        } else if (comparison > 0) {
            BSTNode<T> rightTree = deleteNodeRecursively(currentNode.getRightChild(), data);
            currentNode.setRightChild(rightTree);
        }

        return currentNode;
    }

    private BSTNode<T> deleteNode(BSTNode<T> currentNode) {
        if(currentNode.getLeftChild() == null && currentNode.getRightChild() ==  null) {
            currentNode = null;
        } else if(currentNode.getLeftChild() == null) {
            currentNode = currentNode.getRightChild();
        } else if(currentNode.getRightChild() == null){
            currentNode = currentNode.getLeftChild();
        } else {
            currentNode = swapWithSmallestElementOfRightSubTree(currentNode);
        }
        return currentNode;
    }

    private BSTNode<T> swapWithSmallestElementOfRightSubTree(BSTNode<T> nodeToDelete) {
        BSTNode<T> currentNode = nodeToDelete.getRightChild();
        BSTNode<T> parent = null;
        while(currentNode.getLeftChild() != null) {
            parent = currentNode;
            currentNode = currentNode.getLeftChild();
        }

        nodeToDelete.setData(currentNode.getData());
        if(parent != null) {
            parent.setLeftChild(currentNode.getRightChild());
        } else {
            nodeToDelete.setRightChild(currentNode.getRightChild());
        }
        currentNode = null;
        return nodeToDelete;
    }


    private BSTNode<T> addNodeRecursively(BSTNode<T> currentNode, T data) {
        if(currentNode == null) {
            return new BSTNode<>(data);
        }
        if(data.compareTo(currentNode.getData()) < 0) {
            BSTNode<T> newNode = addNodeRecursively(currentNode.getLeftChild(), data);
            currentNode.setLeftChild(newNode);
        }else {
            BSTNode<T> newNode = addNodeRecursively(currentNode.getRightChild(), data);
            currentNode.setRightChild(newNode);
        }

        return currentNode;
    }


}
