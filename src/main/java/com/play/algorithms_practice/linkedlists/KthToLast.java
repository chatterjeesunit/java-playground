package com.play.algorithms_practice.linkedlists;

import com.play.data_structures.linkedlist.LinkedList;
import com.play.data_structures.linkedlist.Node;

import java.util.Optional;

/**
 * Linked List 2.2
 *
 * Implement an algorithm to find the kth to last element of a singly linked list.
 */
public class KthToLast<T extends Comparable<T>> {

    /**
     * Traverse the entire list.
     * When you have reached K elements from start, then mark kth node as the head of list.
     * after that keep incrementing kth node by 1
     *
     *
     * Time Complexity: O(n) - since we have to traverse entire list
     * Space Complexity: O(1) - only pointer to kth, current node and counters needed.
     *
     */
    public T solve(LinkedList<T> input, int n) {

        Node<T> result = null;

        Node<T> currentNode = input.getHead();
        int counter = 0;
        while(currentNode != null) {
            currentNode = currentNode.getNext();
            counter++;

            if(counter >= n) {
                if(result == null) {
                    result = input.getHead();
                }else {
                    result = result.getNext();
                }
            }
        }

        return Optional.ofNullable(result).map(Node::getData).orElse(null);
    }
}
