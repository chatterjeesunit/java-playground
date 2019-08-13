package com.coursera.algorithms1.week2a.stack;

import java.util.Iterator;

/**
 * Created by sunitc on 5/28/17.
 */
public class AStack<T> implements Stack<T> {

    private T[] items;
    private int next = 0;

    public AStack(int capacity) {
        items = (T[]) new Object[capacity];
    }

    @Override
    public void push(T item) {
        if (items.length == next) resize(items.length * 2);
        items[next++] = item;
    }

    @Override
    public T pop() {
        T item = items[--next];
        items[next] = null;
        if(items.length/4 == next) resize(items.length/2);
        return item;
    }

    @Override
    public boolean isEmpty() {
        return next == 0;
    }

    private void resize(int capacity) {
        System.out.print("Resizing from "+ items.length + " to " + capacity + ".\t" );
        T[] copy = (T[]) new Object[capacity];
        for (int i = 0; i < items.length && i < capacity; i++) {
            copy[i] = items[i];
        }
        items = copy;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int current = 0;

            @Override
            public boolean hasNext() {
                return current < next;
            }

            @Override
            public T next() {
                return items[next - ++current];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not Supported.");
            }
        };
    }
}
