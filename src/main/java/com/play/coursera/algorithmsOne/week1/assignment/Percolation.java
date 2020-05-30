package com.play.coursera.algorithmsOne.week1.assignment;

/**
 * Created by sunitc on 6/4/17.
 */
public class Percolation {

    // Stores true/false - whether or not this site is open.
    // Row numbers and column number access are from 1->n and not from 0->n-1
    private boolean[][] sites;

    // Stores the topMost Row number to which this site is connected to.
    // By default will only contain their own row number.
    // Row numbers and column number access are from 1->n and not from 0->n-1
    private int[][] siteRoots;

    private int numberOfOpenSites = 0;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        sites = new boolean[n][n];
        siteRoots = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sites[i][j] = false;
                siteRoots[i][j] = i + 1;
            }
        }
    }

    private int getSiteRootVal(int row, int col) {
        return siteRoots[row - 1][col - 1];
    }

    private void setSiteRootVal(int row, int col, int value) {
        siteRoots[row - 1][col - 1] = value;
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        int n = siteRoots.length;
        if (!isValid(row, col, n)) throw new IndexOutOfBoundsException();

        if (isOpen(row, col)) return;

        sites[row - 1][col - 1] = true;
        numberOfOpenSites++;


        int currentVal = getSiteRootVal(row, col);
        // check top site
        currentVal = compareAndChangeSiteRootVal(currentVal, row - 1, col, n);

        // check bottom site
        currentVal = compareAndChangeSiteRootVal(currentVal, row + 1, col, n);

        // check left site
        currentVal = compareAndChangeSiteRootVal(currentVal, row, col - 1, n);

        // check right site
        currentVal = compareAndChangeSiteRootVal(currentVal, row, col + 1, n);

        setSiteRootVal(row, col, currentVal);

        changeAllNeighBourValues(currentVal, row, col, n);
    }

    private int compareAndChangeSiteRootVal(int currentVal, int nRow, int nCol, int n) {
        // check if the neighbour indexes are out of bounds.
        if (!isValid(nRow, nCol, n))
            return currentVal;

        if (isOpen(nRow, nCol)) {
            int neighbourVal = getSiteRootVal(nRow, nCol);
            if (currentVal > neighbourVal)
                return neighbourVal;
        }
        return currentVal;
    }

    private void changeAllNeighBourValues(int currentVal, int row, int col, int n) {
        // Left column
        changeSingleNeighbourValue(currentVal, row, col - 1, n);

        // Right column
        changeSingleNeighbourValue(currentVal, row, col + 1, n);

        // Top column
        changeSingleNeighbourValue(currentVal, row - 1, col, n);

        // Bottom column
        changeSingleNeighbourValue(currentVal, row + 1, col, n);
    }

    private void changeSingleNeighbourValue(int currentVal, int nRow, int nCol, int n) {
        if (isValid(nRow, nCol, n) && isOpen(nRow, nCol)
                && getSiteRootVal(nRow, nCol) > currentVal) {
            setSiteRootVal(nRow, nCol, currentVal);
            changeAllNeighBourValues(currentVal, nRow, nCol, n);
        }
    }

    private boolean isValid(int row, int col, int n) {
        return col >= 1 && col <= n && row >= 1 && row <= n;
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        int n = siteRoots.length;
        if (!isValid(row, col, n)) throw new IndexOutOfBoundsException();
        return sites[row - 1][col - 1];
    }

    // is site (row, col) full?
    // A full site is an open site that can be connected to an open site in
    // the top row via a chain of neighboring (left, right, up, down) open sites.
    public boolean isFull(int row, int col) {
        int n = siteRoots.length;
        if (!isValid(row, col, n)) throw new IndexOutOfBoundsException();
        return isOpen(row, col) && getSiteRootVal(row, col) == 1;
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        // Since its a nxn matrix, so siteRoots.length will also give us the num of columns.
        int n = siteRoots.length;
        for (int i = 1; i <= n; i++) {
            if (isFull(n, i)) return true;
        }
        return false;
    }

    private void printSite() {
        int n = siteRoots.length;
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (isOpen(i, j))
                    builder.append("|\t" + getSiteRootVal(i, j) + "\t");
                else
                    builder.append("|\t" + "-" + "\t");
            }
            builder.append("|\n");
        }
        System.out.println(builder.toString());
        System.out.println(percolates());
    }

    // test client (optional)
    public static void main(String[] args) {

        Percolation percolation = new Percolation(6);
        System.out.println(percolation.isFull(1, 1));
        percolation.open(4, 4);
        percolation.open(5, 2);
        percolation.open(2, 3);
        percolation.open(4, 3);
        percolation.open(3, 3);
        percolation.open(4, 1);
        percolation.open(3, 1);
        percolation.open(2, 1);
        percolation.open(1, 2);
        percolation.open(2, 2);
        percolation.open(6, 2);
        percolation.open(5, 3);
        System.out.println("Total sites open = " + percolation.numberOfOpenSites());
        percolation.printSite();
    }
}
