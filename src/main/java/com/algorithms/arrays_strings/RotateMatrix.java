package com.algorithms.arrays_strings;

/**
 * Modification of Arrays & Strings 1.7
 *
 * Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes, write a method to rotate the image by 90 degrees.
 * Can you do this in place?
 *
 *
 */
public class RotateMatrix {

    /**
     * Matrix rotation with extra space
     *  - create a new matrix of exact size.
     *  - do double loop of data and copy from source to target
     *      - row 1 goes into col n
     *      - row 2 goes into col n-1
     *      - row 3 goes into col n-2
     *      - and so on
     *
     *  Time Complexity - O(n^2)
     *  Space Complexity - O(n^2)
     *
     * @param input
     * @return
     */
    public int[][] rotateMatrixWithExtraSpace(int[][] input) {

        int n = input.length;

        int[][] rotatedMatrix = new int[n][n];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                rotatedMatrix[col][n-row-1] = input[row][col];
            }
        }
        return rotatedMatrix;
    }

    /**
     * Matrix rotation in place
     *  - still will require an array of size n
     *  - do layer by layer
     *      - e.g first rotate edges of 4x4 matrix
     *          - move top row to right column
     *          - move right col to bottom row
     *          - move bottom row to left col
     *          - move left col to top row
     *      - then rotate edges of inner 2x2 matrix and so on
     *
     *  Time Complexity - O(n^2)
     *  Space Complexity - O(1) - since we keep track of only 1 element that is overwritten.
     *
     */
    public int[][] rotateMatrixInPlace(int[][] input) {

        int n = input.length;

        for (int i = 0, j=0; i <= n/2 && j <= n/2 ; i++, j++) {
            rotate(input, i, j, n-2*i);
        }
        return input;
    }


    private void rotate(int[][] input, int rowStartIndex, int colStartIndex, int size) {

        if(size <= 1) {
            return;
        }

        int firstRow = rowStartIndex;
        int lastRow = rowStartIndex + size - 1;
        int firstColumn = colStartIndex;
        int lastColumn = colStartIndex + size - 1;

        for (int i = colStartIndex; i < colStartIndex + size - 1 ; i++) {
            int leftElement = input[firstRow + i][firstColumn];

            //move bottom to left
            input[firstRow + i][firstColumn] = input[lastRow][firstColumn + i];

            //move right to bottom
            input[lastRow][firstColumn + i] = input[lastRow - i][lastColumn];

            //move top to right
            input[lastRow - i][lastColumn] = input[firstRow][lastColumn - i];

            //move left to top
            input[firstRow][lastColumn - i] = leftElement;
        }
    }

}
