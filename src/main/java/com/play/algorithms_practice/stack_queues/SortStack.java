package com.play.algorithms_practice.stack_queues;

import com.play.data_structures.stack.Stack;

import java.util.List;

/**
 * Stack and Queues - 3.5
 *
 * Write a program to sort a stack such that the smallest items are on the top.
 * You can use an additional temporary stack, but you may not copy the elements into any other data structure (such as an array).
 * The stack supports the following operations: push, pop, peek, and isEmpty.
 */
public class SortStack<T extends Comparable<T>> {

    public SortStack(int size) {
        this.elements = new Stack<>(size);
    }

    public SortStack(List<T> listOfElements) {
        this.elements = new Stack<>(listOfElements.size());
        listOfElements.stream().forEach(e -> elements.push(e));
    }

    private Stack<T> elements;

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public T peek() {
        return elements.peek();
    }

    public T pop() {
        return elements.pop();
    }

    public void push (T data) {
        elements.push(data);
    }

    public int size () {
        return elements.length();
    }

    public void sort() {
        Stack<T> temp = new Stack<>(size());

        while(!elements.isEmpty()) {
            T top = elements.pop();

            int counter = 0;
            while(!temp.isEmpty() && top.compareTo(temp.peek()) < 0) {
                elements.push(temp.pop());
                counter++;
            }
            temp.push(top);

            while(counter > 0 ){
                temp.push(elements.pop());
                counter--;
            }
        }

        while(!temp.isEmpty()) {
            elements.push(temp.pop());
        }

    }
}
