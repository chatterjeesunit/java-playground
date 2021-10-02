package com.algorithms.arrays_strings;

import java.util.HashSet;
import java.util.Set;

/**
 * Modification of Arrays & Strings 1.8
 *
 * Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column are set to 0.
 *
 *
 */
public class ZeroMatrix {

    /**
     *  - Loop through each row one at a time
     *      - For each row, loop through each column value
     *      - if column is 0, then mark this entire column as 0, and add it to a set to that we can skip this column
     *      in future processing. (otherwise this 0 will be considered in evaluation and entire matrix will become 0)
     *      - after entire row is processed, mark the row as 0 (if row contained a 0)
     *
     *
     *  Time Complexity - O(M*N) - since we have to check each column in each row
     *  Space Complexity - O(N)
     *     - since at max we have to keep track of all columns that had zero in it.
     *     - we can also try to reduce space complexity by storing the set into an bit vector... may become O(1)
     *
     */
    public int[][] solve(int[][] input) {

        if(input == null || input.length == 0) return input;

        int nRows = input.length;
        int nCols = input[0].length;

        Set<Integer> columnsToSkip = new HashSet<>();

        for (int row = 0; row < nRows; row++) {
            boolean markRow = false;
            for (int col = 0; col < nCols; col++) {

                if(columnsToSkip.contains(col)){
                    //This column has already been marked as zero by previous processing.
                    continue;
                }
                if(input[row][col] == 0) {
                    markRow = true;
                    columnsToSkip.add(col);
                    markColumn(input, col, nRows, 0);
                }
            }
            if(markRow) {
                markRow(input, row, nCols, 0);
            }
        }

        return input;
    }


    private void markRow(int[][] matrix, int currentRow, int numberColumns, int newValue) {
        for (int col = 0; col < numberColumns; col++) {
            matrix[currentRow][col] = newValue;
        }
    }


    private void markColumn(int[][] matrix, int currentCol, int numRows, int newValue) {
        for (int row = 0; row < numRows; row++) {
            matrix[row][currentCol] = newValue;
        }
    }
}
