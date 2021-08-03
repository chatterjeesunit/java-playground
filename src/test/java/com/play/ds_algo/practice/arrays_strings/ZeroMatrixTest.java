package com.play.ds_algo.practice.arrays_strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ZeroMatrixTest {

    ZeroMatrix testCls = new ZeroMatrix();


    @Test
    void shouldSolveForA3x4Matrix() {

        int[][] input3x4 = {
                {   1,  2,  3,  4   },
                {   5,  6,  0,  8   },
                {   9,  0, 11, 12  }
        };

        int[][] expectedOutput3x4 = {
                {   1,  0,  0,  4   },
                {   0,  0,  0,  0   },
                {   0,  0,  0,  0  }
        };

        testAndVerify(input3x4, expectedOutput3x4);
    }

    @Test
    void shouldSolveForA6x5Matrix() {

        int[][] input6x5 = {
                {   1,      2,      3,      4,      5    },
                {   6,      7,      8,      9,      10   },
                {   11,     12,     0,      14,     15   },
                {   16,     17,     18,     19,     20   },
                {   21,     22,     23,     24,     25   },
                {   0,      27,     28,     29,     0   }
        };


        int[][] expectedOutput6x5 = {
                {   0,      2,      0,      4,      0    },
                {   0,      7,      0,      9,      0    },
                {   0,      0,      0,      0,      0    },
                {   0,      17,     0,      19,     0    },
                {   0,      22,     0,      24,     0    },
                {   0,      0,      0,      0,      0    }
        };

        testAndVerify(input6x5, expectedOutput6x5);
    }

    private void testAndVerify(int[][] input, int[][] expectedOutput) {
        int nRows = input.length;
        int nCols = input[0].length;
        System.out.println("*******************\n" + nRows + " x " + nCols + " Matrix \n*******************");
        System.out.println("Input Matrix\n---------------------------");
        printMatrix(input);
        System.out.println("Expected Output Matrix\n---------------------------");
        printMatrix(expectedOutput);

        int[][] actualOutput = testCls.solve(input);

        System.out.println("Actual Output Matrix\n---------------------------");
        printMatrix(actualOutput);

        verifyResult(expectedOutput, nRows, actualOutput);
    }


    private void verifyResult(int[][] expectedOutput, int nRows, int[][] actualOutput) {
        for (int row = 0; row < nRows; row++) {
            assertArrayEquals(expectedOutput[row] , actualOutput[row], "Row "+ row + " did not match");

        }
    }


    private void printMatrix(int[][] matrix) {
        int nRows = matrix.length;
        int nCols = matrix[0].length;

        StringBuilder stringBuilder = new StringBuilder((nCols  * 2 + 2) * nRows);
        for (int row = 0; row < nRows; row++) {
            stringBuilder.append("[\t");
            for (int col = 0; col < nCols; col++) {
                stringBuilder.append(matrix[row][col]);
                stringBuilder.append("\t");
            }
            stringBuilder.append("]\n");
        }
        System.out.println(stringBuilder);
    }

}