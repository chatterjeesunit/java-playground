package com.play.ds_algo.practice.data_structures.queue;

import java.util.LinkedList;

/**
 * Implementing Queue using linked list.
 */
public class Queue<T> {

    LinkedList<T> list = new LinkedList<>();

    public void add(T item) {
       list.addLast(item);
    }

    public T remove() {
        peek();
        return list.removeFirst();
    }

    public T peek() {
        if(list.isEmpty()) {
            throw new RuntimeException("Queue is Empty");
        }
        return list.getFirst();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }
}
