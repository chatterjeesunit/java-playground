package com.play.coursera.algorithmsOne.week2a.stack;

import java.util.Iterator;

/**
 * Created by sunitc on 5/28/17.
 */
public class LStack<T> implements Stack<T> {

    private Node<T> first;

    @Override
    public void push(T item) {
       Node<T> newNode = new Node<T>(item, first);
       first = newNode;
    }

    @Override
    public T pop() {
        if(isEmpty()) return null;
        T item = first.getItem();
        first = first.getNext();
        return item;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = first;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T item = current.getItem();
                current = current.getNext();
                return item;
            }
        };
    }
}

class Node<T> {
    private T item;
    private Node<T> next;

    public Node(T item, Node<T> next) {
        this.item = item;
        this.next = next;
    }

    public T getItem() {
        return item;
    }

    public Node<T> getNext() {
        return next;
    }
}