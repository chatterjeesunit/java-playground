package com.play.algorithms_practice.unionfind;

/**
 * Union Find Implementation
 *
 * Used to check if two nodes are connected with each other or not
 *
 * We saw in Quick find that union operation was not scaling if we had to n unions (it would take O(n^2) )
 * In quick find, we modify the union operation to store root of the current tree, instead of updating all nodes with same value.
 * In find we check if roots are equal, then both are connected
 *
 *
 * Time Complexity
 *   - find/connected()
 *      - O(log n) - average cases height of tree will be log n
 *      - O(N) - in worst case where we have a very big tall tree
 *   - union()
 *      - O(log n) - average cases height of tree will be log n
 *      - O(N) - in worst case where we have a very big tall tree
 *   - initialization - O(n)
 *
 *   Space complexity - will require N size array + log n space for recursion to find root.
 *
 *   Later we will see an implementation of Weighted-Union-Find
 *
 */
public class UF {

    private int capacity;
    private int[] elements;

    /**
     * Initialize the array, from 0 to size - 1
     * Set all elements with same value as current index
     */
    public UF(int size) {
        this.elements = new int[size];
        this.capacity = size;
        for (int i = 0; i < this.capacity; i++) {
            elements[i] = i;
        }
    }

    private boolean isValid(int index) {
        return index >= 0 && index < this.capacity;
    }


    private int root(int p) {
        if(elements[p] == p) {
            return p;
        }
        return root(elements[p]);
    }


    /**
     * Two index positions are connected, if they have the same root element
     */
    public boolean isConnected(int p, int q) {
        if(!isValid(p) || !isValid(q)) {
            return false;
        }

        return root(p) == root(q);
    }

    /**
     * Find root of p, q
     * replace root of p, with root of q
     */
    public void union(int p, int q) {
        if(!isValid(p) || !isValid(q)) {
            return;
        }


        int pRoot = root(p);
        int qRoot = root(q);

        if(qRoot == pRoot) {
            return;
        }
        elements[pRoot] = qRoot;
    }

}
