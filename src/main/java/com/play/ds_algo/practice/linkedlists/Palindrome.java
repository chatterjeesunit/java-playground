package com.play.ds_algo.practice.linkedlists;

import com.play.ds_algo.practice.data_structures.linkedlist.LinkedList;
import com.play.ds_algo.practice.data_structures.linkedlist.Node;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Stack;

/**
 * Linked List 2.6
 *
 * Implement a function to check if a linked list is a palindrome.
 *
 */
public class Palindrome<T extends Comparable<T>> {

    /**
     * Reverse first linked list
     * Then traverse both linked list from head to tail and compare nodes.
     * if all nodes are equal then
     *
     * if all characters are same then it is a palindrome.
     *
     * Time Complexity: O(n) - even if we have to traverse the linked list twice
     * Space Complexity: O(n) - as we need to store reverse linked list
     *
     */
    public boolean checkIfPalindromeUsingListReversal(LinkedList<T> input) {
        if(input == null || input.getHead() == input.getTail()) {
            return true;
        }

        LinkedList<T> reversedLinkedList = input.reverse();

        Node<T> n1 = input.getHead();
        Node<T> n2 = reversedLinkedList.getHead();
        boolean isPalindrome = true;
        while(n1 != null && n2 != null ) {
            if(!n1.getData().equals(n2.getData())) {
                isPalindrome = false;
                break;
            }
            n1 = n1.getNext();
            n2 = n2.getNext();
        }

        return isPalindrome;
    }


    /**
     * Use a fast and slow pointer to traverse the list.
     * Keep putting the nodes of slow pointer on to a stack
     * When fast pointer has reached the end, the slow pointer will be at middle
     * Then continue the slow pointer, and check each element with element at top of stack.
     *
     * Time Complexity: O(n) - even if we have to traverse the linked list once
     * Space Complexity: O(n) - as we need to store atleast half linked list on the stack
     *
     */
    public boolean checkIfPalindromeUsingStack(LinkedList<T> input) {
        if(input == null || input.getHead().getNext() == null) {
            return true;
        }

        Stack<T> stack = new Stack<>();

        Node<T> slowPointer = input.getHead();
        Node<T> fastPointer = input.getHead().getNext();
        stack.push(slowPointer.getData());

        while( fastPointer != null && fastPointer.getNext() != null) {
            slowPointer = slowPointer.getNext(); //moves 1 node at a time
            fastPointer = fastPointer.getNext().getNext(); // moves 2 node at a time

            //handle odd palindrome
            if(fastPointer != null) {
                stack.push(slowPointer.getData());
            }
        }

        boolean isPalindrome = true;
        while(slowPointer.getNext() != null) {
            slowPointer = slowPointer.getNext();
            T data = stack.pop();
            if(!data.equals(slowPointer.getData())) {
                isPalindrome = false;
                break;
            }
        }
        return isPalindrome;
    }


    /**
     * Reverse using recursion.
     *  - first find length of string.
     *  - recurse only till middle of list
     *  - return value of recursion is
     *      - whether current value matches the opposite value for palindrome
     *      - what the next node is for next comparison in recursion
     *
     * Time Complexity - O(n)
     * Space Complexity - O(n)
     *
     *
     */
    public boolean checkIfPalindromeUsingRecursion(LinkedList<T> input) {
        if(input == null || input.getHead().getNext() == null) {
            return true;
        }

        int numNodes = findLength(input);

        RecursionData<T> data = checkRecursively(input.getHead(), 1, numNodes);
        return data.isDataMatched();
    }


    private RecursionData<T> checkRecursively(Node<T> currentNode, int counter, int totalNodes) {
        if(counter > totalNodes/2) {
            Node<T> nodeForComparisonCheck = currentNode;
            if(totalNodes % 2 != 0) {
                nodeForComparisonCheck = nodeForComparisonCheck.getNext();
            }
            return new RecursionData(true, nodeForComparisonCheck);
        }
        RecursionData<T> data = checkRecursively(currentNode.getNext(), counter+1, totalNodes);
        return new RecursionData<>(
                data.isDataMatched() && currentNode.getData().equals(data.getNodeForComparison().getData()),
                data.getNodeForComparison().getNext());
    }


    private int findLength(LinkedList<T> list) {
        Node<T> currentNode = list.getHead();
        int counter = 0;
        while(currentNode != null) {
            counter++;
            currentNode = currentNode.getNext();
        }
        return counter;
    }
}

@Getter
@AllArgsConstructor
class RecursionData<T extends Comparable<T>> {
    boolean dataMatched;
    Node<T> nodeForComparison;
}
