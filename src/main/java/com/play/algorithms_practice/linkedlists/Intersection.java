package com.play.algorithms_practice.linkedlists;

import com.play.data_structures.linkedlist.Node;

/**
 * Linked List 2.7
 *
 * Given two (singly) linked lists, determine if the two lists intersect.
 * Return the intersecting node.
 * Note that the intersection is defined based on reference, not value.
 * That is, if the kth node of the first linked list is the exact same node (by reference) as the jth node of the second linked list,
 * then they are intersecting.
 *
 */
public class Intersection<T extends Comparable<T>> {


    /**
     * Two lists can be of different lengths m, n
     * There intersection is however at same distance from there tails.
     * First find length of both lists.
     * Then discard (m-n) nodes of the longer list.
     * Then traverse both list one node at a time and check if the node reference is same
     *
     * Time Complexity: O(M + N)
     * Space Complexity: O(1)
     *
     */
    public Node<T> solve(Node<T> headOfFirstList, Node<T> headOfSecondList) {

        int numNodesFirstList = findLength(headOfFirstList);
        int numNodesSecondList = findLength(headOfSecondList);


        Node currNodeFirstList = moveCurrNodePointer(numNodesFirstList, numNodesSecondList, headOfFirstList);
        Node currNodeSecondList = moveCurrNodePointer(numNodesSecondList, numNodesFirstList, headOfSecondList);

        while(currNodeFirstList != null && currNodeSecondList != null) {
            if(currNodeFirstList == currNodeSecondList) {
                return currNodeFirstList;
            }
            currNodeFirstList = currNodeFirstList.getNext();
            currNodeSecondList = currNodeSecondList.getNext();
        }

        return null;
    }

    private Node<T> moveCurrNodePointer(int n1, int n2, Node<T> head) {
        Node currNode = head;
        for (int i = 0; i < n1 - n2; i++) {
            currNode = currNode.getNext();
        }
        return currNode;
    }

    private int findLength(Node<T> head) {
        Node<T> currentNode = head;
        int counter = 0;
        while(currentNode != null) {
            counter++;
            currentNode = currentNode.getNext();
        }
        return counter;
    }

}
