package com.play.data_structures.tree;

import lombok.Getter;

public class MaxHeap<T extends Comparable<T>> {

    int capacity;

    @Getter
    int length;

    T[] elements;

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.length = 0;
        elements = (T[]) new Comparable[capacity];
    }

    /**
     * Time Complexity : O(log n)
     *    - we add element at last position, then climb up comparing to parents.
     *    - so a heap with n nodes, max depth = log n, which is the max comparisons that will be done
     */
    public void add(T data) {
        if(length == capacity) {
            throw new IndexOutOfBoundsException("Max Heap is full");
        }
        elements[length++] = data;

        int currentPosition = length - 1;
        while(currentPosition > 0) {
            int parentNodePosition = getParentPosition(currentPosition);
            T parentElement = elementAt(parentNodePosition);

            if(parentElement != null) {
                if(parentElement.compareTo(data) < 0) {
                    swapElements(currentPosition, parentNodePosition);
                } else {
                    break;
                }
            }
            currentPosition = parentNodePosition;
        }

    }

    public T getMax() {
        return length > 0 ? elements[0] : null;
    }


    /**
     * Time Complexity : O(log n)
     *    - we remove element at start and swap it with last element.
     *    - then we compare with either childs at max
     *    - so a heap with n nodes, max depth = log n,
     *    - so at max 2 * log n comparison -> O(log n)
     */
    public T removeMax() {
        T maxElement = getMax();

        swapElements(0, length - 1);

        elements[--length] = null;

        int currentPos = 0;
        while(currentPos < length) {
            int leftChildPos = getLeftChildPos(currentPos);
            int rightChildPos = leftChildPos  + 1;

            T currentElement = elementAt(currentPos);
            T leftChild = elementAt(leftChildPos);
            T rightChild = elementAt(rightChildPos);

            //if no child, then break out of loop.
            if(leftChild == null) {
                break;
            }

            // decide whether to swap with left or right child
            int positionForSwap = leftChildPos;
            if(rightChild != null && rightChild.compareTo(leftChild) > 0) {
                positionForSwap = rightChildPos;
            }

            if(currentElement.compareTo(elementAt(positionForSwap)) < 0) {
                swapElements(currentPos, positionForSwap);
                currentPos = positionForSwap;
            } else  {
                break;
            }

        }


        return maxElement;
    }

    private void swapElements(int index1, int index2) {
        T temp = elementAt(index1);
        elements[index1] = elementAt(index2);
        elements[index2] = temp;
    }

    private T elementAt(int pos) {
        return isWithinHeapLimits(pos) ? elements[pos] : null;
    }

    private boolean isOdd(int n) {
        return n % 2 != 0;
    }

    private boolean isWithinHeapLimits(int pos) {
        return pos >= 0 && pos < length;
    }

    private int getParentPosition(int pos) {
        return isOdd(pos) ? pos/2 : pos/2 - 1;
    }

    private int getLeftChildPos(int pos) {
        return pos * 2 + 1;
    }


    private void printHeap() {
        System.out.print("[ ");
        for (int i = 0; i < length; i++) {
            System.out.print(elements[i] + ", ");
        }
        System.out.println(" ]");
    }
}