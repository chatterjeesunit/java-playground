package com.algorithms.arrays_strings;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class RotateMatrixTest {
    RotateMatrix testCls = new RotateMatrix();

    int[][] input1x1 =  {{ 0 }};
    int[][] expectedRotatedMatrix1x1 = {{0}};



    int[][] input2x2 =  {
            { 1, 2 },
            { 3, 4 }
    };
    int[][] expectedRotatedMatrix2x2 = {
            { 3, 1 },
            { 4, 2 }
    };




    int[][] input3x3 =  {
            { 1, 2, 3},
            { 4, 5, 6},
            { 7, 8, 9}
    };
    int[][] expectedRotatedMatrix3x3 = {
            { 7, 4, 1},
            { 8, 5, 2},
            { 9, 6, 3}
    };


    int[][] input4x4 =  {
            { 1,    2,      3,      4},
            { 5,    6,      7,      8},
            { 9,    10,     11,     12},
            { 13,   14,     15,     16}
    };
    int[][] expectedRotatedMatrix4x4 = {
            { 13,    9,      5,     1},
            { 14,    10,     6,     2},
            { 15,    11,     7,     3},
            { 16,    12,     8,     4}
    };

    @Nested
    class InPlaceMatrixRotations {

        @Test
        void shouldRotateOneByOne2DMatrix() {
            System.out.println("Initial : \n" + printMatrix(input1x1));
            int[][] actualOutput = testCls.rotateMatrixInPlace(input1x1);
            testMatrixEqualityAfterRotation(actualOutput, expectedRotatedMatrix1x1);
        }

        @Test
        void shouldRotateTwoByTwo2DMatrix() {
            System.out.println("Initial : \n" + printMatrix(input2x2));
            int[][] actualOutput = testCls.rotateMatrixInPlace(input2x2);
            testMatrixEqualityAfterRotation(actualOutput, expectedRotatedMatrix2x2);
        }

        @Test
        void shouldRotateThreeByThree2DMatrix() {
            System.out.println("Initial : \n" + printMatrix(input3x3));
            int[][] actualOutput = testCls.rotateMatrixInPlace(input3x3);
            testMatrixEqualityAfterRotation(actualOutput, expectedRotatedMatrix3x3);
        }

        @Test
        void shouldRotateFourByFour2DMatrix() {
            System.out.println("Initial : \n" + printMatrix(input4x4));
            int[][] actualOutput = testCls.rotateMatrixInPlace(input4x4);
            testMatrixEqualityAfterRotation(actualOutput, expectedRotatedMatrix4x4);
        }
    }


    @Nested
    class MatrixRotationsWithExtraSpace {

        @Test
        void shouldRotateOneByOne2DMatrix() {
            System.out.println("Initial : \n" + printMatrix(input1x1));
            int[][] actualOutput = testCls.rotateMatrixWithExtraSpace(input1x1);
            testMatrixEqualityAfterRotation(actualOutput, expectedRotatedMatrix1x1);
        }

        @Test
        void shouldRotateTwoByTwo2DMatrix() {
            System.out.println("Initial : \n" + printMatrix(input2x2));
            int[][] actualOutput = testCls.rotateMatrixWithExtraSpace(input2x2);
            testMatrixEqualityAfterRotation(actualOutput, expectedRotatedMatrix2x2);
        }

        @Test
        void shouldRotateThreeByThree2DMatrix() {
            System.out.println("Initial : \n" + printMatrix(input3x3));
            int[][] actualOutput = testCls.rotateMatrixWithExtraSpace(input3x3);
            testMatrixEqualityAfterRotation(actualOutput, expectedRotatedMatrix3x3);
        }

        @Test
        void shouldRotateFourByFour2DMatrix() {
            System.out.println("Initial : \n" + printMatrix(input4x4));
            int[][] actualOutput = testCls.rotateMatrixWithExtraSpace(input4x4);
            testMatrixEqualityAfterRotation(actualOutput, expectedRotatedMatrix4x4);
        }
    }



    private void testMatrixEqualityAfterRotation(int[][] actualOutput, int[][] expectedRotatedMatrix) {

        System.out.println("Expected : \n" + printMatrix(expectedRotatedMatrix));
        System.out.println("Actual : \n" + printMatrix(actualOutput));

        for (int i = 0; i < expectedRotatedMatrix.length; i++) {
            System.out.println("Row : " + i);
            assertArrayEquals(expectedRotatedMatrix[i], actualOutput[i]);
        }
    }


    private String printMatrix(int[][] matrix) {
        StringBuilder stringBuilder = new StringBuilder((matrix.length  * 2 + 2) * matrix.length);
        for (int row = 0; row < matrix.length; row++) {
            stringBuilder.append("[\t");
            for (int col = 0; col < matrix.length; col++) {
                stringBuilder.append(matrix[row][col]);
                stringBuilder.append("\t");
            }
            stringBuilder.append("]\n");
        }
        return stringBuilder.toString();
    }
}