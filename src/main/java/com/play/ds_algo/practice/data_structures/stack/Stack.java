package com.play.ds_algo.practice.data_structures.stack;

import java.util.Arrays;

public class Stack<T> {
    private Object[] dataArray;
    private int length = -1;
    private int capacity;

    public Stack(final int size) {
        capacity = size;
        dataArray = new Object[size];
    }

    public void push(T data) {
        length++;
        if(length >= capacity) {
            resize();
        }
        dataArray[length] = data;
    }

    private void resize() {
        dataArray = Arrays.copyOf(dataArray, capacity * 2);
        capacity = capacity * 2;
    }

    public T pop() {
        T data = peek();
        dataArray[length] = null;
        length--;
        return data;
    }

    public T peek() {
        if(length < 0) {
            throw new RuntimeException("Stack is Empty");
        }
        return (T)dataArray[length];
    }

    public int length() {
        return length + 1;
    }

    public int getCapacity() {
        return capacity;
    }
}
