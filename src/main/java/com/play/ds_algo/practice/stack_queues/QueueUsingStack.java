package com.play.ds_algo.practice.stack_queues;

import java.util.Stack;

/**
 * Stack and Queues - 2.4
 *
 * Implement a MyQueue class which implements a queue using two stacks.
 */
public class QueueUsingStack<T> {

    Stack<T> stackOne = new Stack<>();
    Stack<T> stackTwo = new Stack<>();

    public boolean isEmpty() {
        return stackOne.isEmpty() && stackTwo.isEmpty();
    }

    public void add(T element) {
        stackOne.add(element);
    }

    public T remove() {
        if(!stackTwo.isEmpty()) {
            return stackTwo.pop();
        }
        while(!stackOne.isEmpty()) {
            stackTwo.push(stackOne.pop());
        }

        return stackTwo.pop();
    }

    public int size() {
        return stackOne.size() + stackTwo.size();
    }

    public T peek() {
        if(!stackTwo.isEmpty()) {
            return stackTwo.peek();
        }
        while(!stackOne.isEmpty()) {
            stackTwo.push(stackOne.pop());
        }

        return stackTwo.peek();
    }
}
