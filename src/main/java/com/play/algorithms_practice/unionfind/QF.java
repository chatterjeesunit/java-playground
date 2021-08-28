package com.play.algorithms_practice.unionfind;

/**
 * Quick Find Implementation
 *
 * Used to check if two nodes are connected with each other or not
 *
 * Time Complexity
 *   - find/connected() - O(1)
 *   - union() - O(n)
 *   - initialization - O(n)
 *
 *   Although find is very fast, but union of n nodes will have complexity of O(n^2) which is not good.
 *
 *
 *   Space complexity - will require N size array
 *
 *   So later we will see an implementation of Union-Find
 *
 */
public class QF {

    private int capacity;
    private int[] elements;

    /**
     * Initialize the array, from 0 to size - 1
     * Set all elements with same value as current index
     */
    public QF(int size) {
        this.elements = new int[size];
        this.capacity = size;
        for (int i = 0; i < this.capacity; i++) {
            elements[i] = i;
        }
    }

    private boolean isValid(int index) {
        return index >= 0 && index < this.capacity;
    }

    /**
     * Two index positions are connected, if they have same value in the array.
     */
    public boolean isConnected(int p, int q) {
        if(!isValid(p) || !isValid(q)) {
            return false;
        }

        return elements[p] == elements[q];
    }

    /**
     * Set all elements with value of pIndex, to the value at qIndex
     */
    public void union(int p, int q) {
        if(!isValid(p) || !isValid(q) || isConnected(p,q)) {
            return;
        }

        int pVal= elements[p];
        int qVal = elements[q];
        for (int i = 0; i < capacity; i++) {
            if(elements[i] == pVal) {
                elements[i] = qVal;
            }
        }
    }

}
