package com.algorithms.linkedlists;

import com.data_structures.linkedlist.LinkedList;
import com.data_structures.linkedlist.Node;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

/**
 * Linked List 2.5
 *
 * You have two numbers represented by a linked list, where each node contains a single digit.
 * The digits are stored in reverse order, such that the 1 's digit is at the head of the list.
 * Write a function that adds the two numbers and returns the sum as a linked list.
 * EXAMPLE
 * Input:(7-> 1 -> 6) + (5 -> 9 -> 2).
 * That is, 617 + 295.
 * Output:2 -> 1 -> 9. That is, 912.
 *
 * FOLLOW UP
 * Suppose the digits are stored in forward order. Repeat the above problem. EXAMPLE
 * lnput:(6 -> 1 -> 7) + (2 -> 9 -> 5).
 * That is, 617 + 295.
 * Output:9 -> 1 -> 2.That is, 912.
 *
 *
 * Assumption for Second one : number of digits is same in both
 */
public class SumLists {


    /**
     * Traverse through each list. Do not assume that they will be of same length
     *  - add digits at same position + previous position carry
     *  - create new node from units place of sum , and add to new linked list
     *  - carry = value at tens place
     *
     * Time Complexity - for two list of size m and n, where M > N  = O(M)
     * Space Complexity - O(M)
     *
     */
    public LinkedList<Integer> solveReverseSum(LinkedList<Integer> num1, LinkedList<Integer> num2) {

        Node<Integer> currentNum1 = num1.getHead();
        Node<Integer> currentNum2 = num2.getHead();

        LinkedList<Integer> finalResult = new LinkedList<>();

        int carry = 0;

        while(currentNum1 != null || currentNum2 != null) {
            Optional<Node<Integer>> currentNum1Optional = Optional.ofNullable(currentNum1);
            Optional<Node<Integer>> currentNum2Optional = Optional.ofNullable(currentNum2);

            int sum = currentNum1Optional.map(Node::getData).orElse(0)
                        + currentNum2Optional.map(Node::getData).orElse(0)
                        + carry;

            int newNodeValue = sum % 10;
            carry = sum / 10;

            finalResult.addNodeToTail(newNodeValue);

            currentNum1 = currentNum1Optional.map(Node::getNext).orElse(null);
            currentNum2 = currentNum2Optional.map(Node::getNext).orElse(null);
        }

        if(carry > 0) {
            finalResult.addNodeToTail(carry);
        }

        return finalResult;
    }


    /**
     * First append 0's to the start of shorter number, to ensure both linked list have same length.
     * Then recursively calculate sum of child nodes.
     * Return current result linked list and the current carry
     *
     * Time Complexity : O(m) , where m, n and size of both linked lists
     *  - even though we traverse both lists twice, the complexity will be in order of size of bigger linked list
     *
     *  Space Complexity: O(n) - due to recursion
     *
     */
    public LinkedList<Integer> orderedSum(LinkedList<Integer> num1, LinkedList<Integer> num2) {

        int numDigitsNumberOne = lengthOfLinkedList(num1);
        int numDigitsNumberTwo = lengthOfLinkedList(num2);


        if(numDigitsNumberOne > numDigitsNumberTwo) {
            addZeroNodesToStartOfNumber(num2, numDigitsNumberOne - numDigitsNumberTwo);
        } else {
            addZeroNodesToStartOfNumber(num1, numDigitsNumberTwo - numDigitsNumberOne);
        }

        SumResult recursiveResult  = calculateSum(num1.getHead(), num2.getHead());

        LinkedList<Integer> finalSum = recursiveResult.getResult();
        if(recursiveResult.getCarry() > 0) {
            finalSum.addNodeToHead(recursiveResult.getCarry());
        }
        return finalSum;
    }

    private SumResult calculateSum(Node<Integer> n1, Node<Integer> n2) {
        if(n1 == null || n2 == null) {
            return new SumResult(0, new LinkedList<Integer>());
        }
        SumResult recursiveResult = calculateSum(n1.getNext(), n2.getNext());
        int sum = n1.getData() + n2.getData() + recursiveResult.getCarry();
        int digitValue = sum % 10;
        LinkedList<Integer> finalSum = recursiveResult.getResult();
        finalSum.addNodeToHead(digitValue);
        return new SumResult(sum / 10, finalSum);
    }


    private int lengthOfLinkedList(LinkedList<Integer> list) {
        int counter = 0;
        Node<Integer> currentNode = list.getHead();
        while(currentNode != null) {
            counter++;
            currentNode = currentNode.getNext();
        }

        return counter;
    }


    private void addZeroNodesToStartOfNumber(LinkedList<Integer> list, int numOfNodesToAdd) {
        for (int i = 0; i < numOfNodesToAdd; i++) {
            list.addNodeToHead(0);
        }
    }

    @Getter
    @AllArgsConstructor
    class SumResult {
        int carry;
        LinkedList<Integer> result;
    }
}
