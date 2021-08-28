package com.play.algorithms_practice.linkedlists;

import com.play.data_structures.linkedlist.Node;

/**
 * Linked List 2.8
 *
 * Given a circular linked list, implement an algorithm that returns the node at the beginning of the loop.
 * DEFINITION
 * Circular linked list: A (corrupt) linked list in which a node's next pointer points to an earlier node, so as to make a loop in the linked list.
 * EXAMPLE
 * Input: A -> B -> C -> D -> E -> C[the same C as earlier]
 * Output: C
 *
 */
public class LoopDetection<T extends Comparable<T>> {


    /**
     * First detect the loop in the linklist
     *  - start one slow pointer that increments one node at a time
     *  - one fast pointer that increments 2 node at a time
     *  - if both meet at some node then there is loop in linked list
     *
     *  Suppose the meeting happens at k nodes from the head, and loop node is j nodes from start.
     *  This means that meeting node is 'j' nodes from loop node.
     *
     *  So if we increment the slow pointer now j nodes, then we will reach loop node.
     *
     *  e.g A -> B -> C -> D -> E -> C
     *    n = total nodes in list = 5
     *    j = dist of loop node from head node = 2
     *    k = meeting node = D node = 3 nodes from head
     *    so if we walk j nodes (2 nodes) from D node, then we will reach C node
     *
     *  Time Complexity: O(n) - might require more than one traversal of list, but is proportional to n.
     *  Space Complexity: O(1) - no additional space requirement
     *
     * @param headerNode
     * @return
     */
    public Node<T> findLoopNode(Node<T> headerNode) {
        if(headerNode == null || headerNode.getNext() == null) {
            return null;
        }

        Node<T> slow = headerNode;
        Node<T> fast = headerNode;

        boolean loopFound = false;
        while(slow != null && fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();

            if(slow == fast) {
                loopFound = true;
                break;
            }
        }

        if(loopFound) {
            slow = headerNode;
            while(slow != fast) {
                slow = slow.getNext();
                fast = fast.getNext();
            }
            return slow;
        }

        return null;
    }
}
