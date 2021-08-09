package com.play.ds_algo.practice.data_structures.stack;

import java.util.Arrays;

/**
 * Implementing Stack using Arrays
 *
 * @param <T>
 */
public class Stack<T> {
    private Object[] dataArray;
    private int lastElementIndex = -1;
    private int capacity;

    public Stack(final int size) {
        capacity = size;
        dataArray = new Object[size];
    }

    public void push(T data) {
        lastElementIndex++;
        if(lastElementIndex >= capacity) {
            resize();
        }
        dataArray[lastElementIndex] = data;
    }

    private void resize() {
        dataArray = Arrays.copyOf(dataArray, capacity * 2);
        capacity = capacity * 2;
    }

    public T pop() {
        T data = peek();
        dataArray[lastElementIndex] = null;
        lastElementIndex--;
        return data;
    }

    public T peek() {
        if(isEmpty()) {
            throw new RuntimeException("Stack is Empty");
        }
        return (T)dataArray[lastElementIndex];
    }

    public int length() {
        return lastElementIndex + 1;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isEmpty() {
        return lastElementIndex < 0;
    }
}
