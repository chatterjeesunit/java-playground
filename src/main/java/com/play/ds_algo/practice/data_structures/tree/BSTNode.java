package com.play.ds_algo.practice.data_structures.tree;

import lombok.Getter;
import lombok.Setter;

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

    @Override
    public int compareTo(BSTNode<T> other) {
        if(other == null) {
            throw new NullPointerException();
        }
        return this.getData().compareTo(other.getData());
    }


    public BSTNode<T> addNode(T data) {
        return addNode(this, data);
    }

    public BSTNode<T> addNode(BSTNode<T> node, T data) {
        if(node == null) {
            return new BSTNode<>(data);
        }
        if(data.compareTo(node.getData()) < 0) {
            BSTNode<T> newNode = addNode(node.getLeftChild(), data);
            node.setLeftChild(newNode);
        }else {
            BSTNode<T> newNode = addNode(node.getRightChild(), data);
            node.setRightChild(newNode);
        }

        return node;
    }


}
