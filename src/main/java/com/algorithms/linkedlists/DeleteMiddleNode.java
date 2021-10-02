package com.algorithms.linkedlists;

import com.data_structures.linkedlist.Node;

/**
 * Linked List 2.3
 *
 * Implement an algorithm to delete a node in the middle (i.e., any node but the first and last node, not necessarily the exact middle)
 * of a singly linked list, given only access to that node.
 * EXAMPLE
 * Input:the node c from the linked lista->b->c->d->e->f
 * Result: nothing is returned, but the new linked list looks likea->b->d->e- >f
 *
 *
 * Assumption - you dont have access to head or tail of the linked list, and you just have access to the node to be deleted
 */
public class DeleteMiddleNode<T extends Comparable<T>> {


    /**
     * We dont have access to previous node of node to be deleted.
     * Update node to be deleted's data and next pointer to same value as of next node
     * And then delete next node
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     */
    public void solve(Node<T> nodeToDelete) {

        Node<T> nextNode = nodeToDelete.getNext();

        if(nextNode == null) {
            throw new UnsupportedOperationException("Cannot delete the last node.");
        }

        nodeToDelete.setData(nextNode.getData());
        nodeToDelete.setNext(nextNode.getNext());
    }
}
