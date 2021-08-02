package com.play.ds_algo.coursera.algorithms_1.week2a.assignment;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by sunitc on 5/28/17.
 */
public class Deque<Item> implements Iterable<Item> {

    private DequeNode<Item> first;
    private DequeNode<Item> last;
    private int size;


    private class DequeNode<Item> {
        private Item item;
        private DequeNode<Item> previous;
        private DequeNode<Item> next;


        public DequeNode(Item item, DequeNode<Item> previous, DequeNode<Item> next) {
            this.item = item;
            this.previous = previous;
            this.next = next;
        }
    }

    // construct an empty deque
    public Deque() {
        size = 0;
        first = null;
        last = first;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException();
        DequeNode<Item> newNode = new DequeNode<Item>(item, null, first);
        if (first != null)
            first.previous = newNode;
        first = newNode;
        if (last == null)
            last = newNode;
        size++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) throw new NullPointerException();
        DequeNode<Item> newNode = new DequeNode<Item>(item, last, null);
        if (last != null)
            last.next = newNode;
        last = newNode;
        if (first == null)
            first = newNode;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();

        DequeNode<Item> currentNode = first;
        first = currentNode.next;
        if (first != null)
            first.previous = null;
        if (last == currentNode)
            last = null;
        currentNode.next = null;
        size--;
        return currentNode.item;

    }

    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();

        DequeNode<Item> currentNode = last;
        last = last.previous;
        if (last != null)
            last.next = null;
        if (first == currentNode)
            first = null;
        currentNode.previous = null;
        size--;
        return currentNode.item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private DequeNode<Item> current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                if (current == null) throw new NoSuchElementException();
                DequeNode<Item> node = current;
                current = current.next;
                return node.item;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    // unit testing (optional)
    public static void main(String[] args) {

    }
}


