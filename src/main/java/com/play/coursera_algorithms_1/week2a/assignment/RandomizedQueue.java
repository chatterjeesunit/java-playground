package com.play.coursera_algorithms_1.week2a.assignment;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by sunitc on 5/28/17.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int first;
    private int last;

    // construct an empty randomized queue
    public RandomizedQueue() {
        this(10);
    }

    private RandomizedQueue(int capacity) {
        items = (Item[]) new Object[capacity];
        first = 0;
        last = 0;
    }

    // unit testing (optional)
    public static void main(String[] args) {
    }

    // is the queue empty?
    public boolean isEmpty() {
        return first >= last;
    }

    // return the number of items on the queue
    public int size() {
        return last - first;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        if (last == items.length) resize();
        items[last++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            System.out.println("Empty Queue : " + first + ", " + last);
            throw new NoSuchElementException();
        }
        StdRandom.shuffle(items, first, last);
        Item item = items[first];
        items[first++] = null;
        if (isEmpty()) {
            first = 0;
            last = 0;
        } else if (size() <= items.length / 4) resize();
        return item;
    }

    // return (but do not remove) a random item
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        StdRandom.shuffle(items, first, last);
        return items[first];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RQIterator<Item>(items, first, last);
    }


    private void resize() {

        int capacity = items.length;
        if (size() <= items.length / 4)
            capacity = capacity / 2;
        else if (size() == items.length)
            capacity = items.length * 2;
        Item[] copy = (Item[]) new Object[capacity];
        int newLast = 0;
        for (int i = 0, j = first; (i < capacity && i < items.length) && j < last; i++, j++) {
            copy[i] = items[j];
            newLast = i;
        }
        items = copy;
        first = 0;
        last = ++newLast;
    }

    private class RQIterator<Item> implements Iterator<Item> {

        private Item[] iteratorItems;
        private int first;
        private int last;

        public RQIterator(Item[] items, int lo, int high) {
            iteratorItems = items.clone();
            if (lo < high)
                StdRandom.shuffle(iteratorItems, lo, high);
            first = lo;
            last = high;
        }

        @Override
        public boolean hasNext() {
            return first < last;
        }

        @Override
        public Item next() {
            if (first >= last) throw new NoSuchElementException();
            return iteratorItems[first++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}



