package com.play.algorithms_practice.stack_queues;

import java.util.Stack;

/**
 * Stack and Queues - 3.2
 *
 * How would you design a stack which, in addition to push and pop, has a function min which returns the minimum element?
 * Push, pop and min should all operate in 0(1) time.
 */
public class StackMin<T extends Comparable<T>> {

    Stack<T> elements = new Stack<>();
    Stack<T> minElements = new Stack<>();

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public int size() {
        return elements.size();
    }

    public void push(T data) {
        elements.push(data);
        if(minElements.isEmpty() || data.compareTo(minElements.peek()) < 0) {
            minElements.push(data);
        }
    }

    public T pop() {
        T element = elements.pop();

        if(element.equals(minElements.peek())) {
            minElements.pop();
        }
        return element;
    }

    public T peek() {
        return elements.peek();
    }

    public T min() {
        return minElements.peek();
    }
}
