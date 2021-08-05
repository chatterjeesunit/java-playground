package com.play.ds_algo.practice.linkedlists;

import com.play.ds_algo.practice.ds.linkedlist.LinkedList;
import com.play.ds_algo.practice.ds.linkedlist.Node;

import java.util.HashSet;
import java.util.Set;

/**
 * Linked List 2.1
 *
 * Write code to remove duplicates from an unsorted linked list.
 * How would you solve this problem if a temporary buffer is not allowed?
 */
public class RemoveDuplicates<T> {

    /**
     * Parse through the linked list once.
     * Keep a track of all unique elements traversed in a separate hashset
     * If an item is encountered, then remove it from linked list else keep traversing.
     *
     * Time Complexity: O(n) - since we have to traverse the entire linked list once.
     * Space Complexity: O(n) - at max list may not have any duplicates
     */
    public LinkedList<T> solveWithBuffer(LinkedList<T> input) {

        Set<T> nodesAlreadyFound = new HashSet<>();

        Node<T> prevNode = input.getHead();
        Node<T> currNode = prevNode.getNext();
        nodesAlreadyFound.add(prevNode.getData());

        while(currNode != null) {
            if(nodesAlreadyFound.contains(currNode.getData())) {
                removeCurrentNode(prevNode, currNode);
            } else {
                nodesAlreadyFound.add(currNode.getData());
                prevNode = currNode;
            }
            currNode = prevNode.getNext();
        }

        return input;
    }


    /**
     * Parse through the linked list once.
     * For each element of linked list check if this element has occured anywhere in the linked list again
     * If yes delete all occurences
     * Repeat this process for each element of linked list.
     *
     * Time Complexity: O(n^2) - since we have to traverse the entire linked list twice.
     * Space Complexity: O(1) - no extra space required
     */
    public LinkedList<T> solveWithoutBuffer(LinkedList<T> input) {

        Node<T> node = input.getHead();

        while(node != null) {

            Node<T> prevNode = node;
            Node<T> currNode = node.getNext();
            while(currNode != null) {
                if(currNode.getData().equals(node.getData())) {
                    removeCurrentNode(prevNode, currNode);
                } else {
                    prevNode = currNode;
                }
                currNode = prevNode.getNext();
            }
            node = node.getNext();
        }

        return input;
    }

    private void removeCurrentNode(Node<T> prevNode, Node<T> currNode) {
        prevNode.setNext(currNode.getNext());
        currNode.setNext(null);
    }
}
