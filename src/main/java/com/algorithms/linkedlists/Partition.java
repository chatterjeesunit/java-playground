package com.algorithms.linkedlists;

import com.data_structures.linkedlist.LinkedList;
import com.data_structures.linkedlist.Node;

/**
 * Linked list 2.4
 *
 * Write code to partition a linked list around a value x, such that all nodes less than x come before all nodes greater than or equal to x.
 * If x is contained within the list, the values of x only need to be after the elements less than x (see below).
 * The partition element x can appear anywhere in the "right partition"; it does not need to appear between the left and right partitions.
 * EXAMPLE
 * Input: 3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1[partition=5]
 * Output: 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8
 * (outputs can vary depending on algo we write. e.g swap vs node delete and insert vs create two list (bigger/smaller) and then merge them
 */
public class Partition<T extends Comparable<T>> {


    /**
     * Time Complexity - O(n)
     * Space Complexity - O(1)
     */
    public LinkedList<T> solveBySwap(LinkedList<T> input, int partitionElement) {

        Node current = input.getHead();
        Node nodeToReplace = current;

        while(current != null) {
            if(current.getData().compareTo(partitionElement) < 0) {
                if(nodeToReplace != current) {
                    swap(nodeToReplace, current);
                }
                nodeToReplace = nodeToReplace.getNext();
            }
            current = current.getNext();

        }

        return input;
    }

    private void swap(Node<T> n1, Node<T> n2) {
        T temp = n2.getData();
        n2.setData(n1.getData());
        n1.setData(temp);
    }



}
