package com.play.coursera.algorithmsOne.week2a.stack;

/**
 * Created by sunitc on 5/28/17.
 */
public interface Stack<T> extends Iterable<T> {

    public void push(T item);

    public T pop();

    public boolean isEmpty();
}
