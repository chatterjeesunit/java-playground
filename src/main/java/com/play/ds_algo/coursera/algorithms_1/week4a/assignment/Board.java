package com.play.ds_algo.coursera.algorithms_1.week4a.assignment;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by sunitc on 6/17/17.
 */
public class Board {

    private int[][] blocks;
    private int n;
    private int zx;
    private int zy;

    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] inputBlocks) {
        n = inputBlocks.length;
        this.blocks = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.blocks[i][j] = inputBlocks[i][j];
                if (this.blocks[i][j] == 0) {
                    zx = i;
                    zy = j;
                }
            }
        }
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of blocks out of place
    public int hamming() {
        int hammingSum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int currentBlock = blocks[i][j];
                int expectedBlock = i * n + (j + 1);
                if (currentBlock > 0 && currentBlock != expectedBlock) {
                    hammingSum++;
                }
            }
        }
        return hammingSum;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int manhattanSum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int currentBlock = blocks[i][j];
                if (currentBlock > 0) {
                    int mod = currentBlock % n;
                    int r = mod > 0 ? currentBlock / n : currentBlock / n - 1;
                    int c = (mod > 0 ? mod : n) - 1;
                    manhattanSum += Math.abs(r - i) + Math.abs(c - j);

                }
            }
        }
        return manhattanSum;
    }


    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }


    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        int i = StdRandom.uniform(n);
        int j = StdRandom.uniform(n);
        int k = StdRandom.uniform(n);
        int l = StdRandom.uniform(n);
        Board newBoard = getNewBoard(i, j, k, l);
        while (this.equals(newBoard)) {
            newBoard = this.twin();
        }
        return newBoard;
    }

    private Board getNewBoard(int i1, int j1, int i2, int j2) {
        Board twinBoard = new Board(blocks);
        exchange(twinBoard.blocks, i1, j1, i2, j2);
        if (twinBoard.zx == i1 && twinBoard.zy == j1) {
            twinBoard.zx = i2;
            twinBoard.zy = j2;
        } else if (twinBoard.zx == i2 && twinBoard.zy == j2) {
            twinBoard.zx = i1;
            twinBoard.zy = j1;
        }
        return twinBoard;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (this == y) return true;
        if (y == null || getClass() != y.getClass()) return false;

        Board board = (Board) y;

        return Arrays.deepEquals(blocks, board.blocks);
    }


    // all neighboring boards
    public Iterable<Board> neighbors() {
        Board thisBoard = this;
        return new Iterable<Board>() {
            @Override
            public Iterator<Board> iterator() {
                return new NeighbourIterator(thisBoard);
            }
        };
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder builder = new StringBuilder("").append(n).append("\n");
        int maxLength = Integer.toString(n * n).length();
        String format = "%" + maxLength + "d";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                builder.append(String.format(format, blocks[i][j])).append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }


    private void exchange(int[][] array, int i, int j, int k, int l) {
        int temp = array[i][j];
        array[i][j] = array[k][l];
        array[k][l] = temp;
    }


    private class NeighbourIterator implements Iterator<Board> {

        private Board[] neighbours = new Board[4];
        private int counter = 0;
        private int iterCount = 0;

        NeighbourIterator(Board board) {
            if (board.zx < board.n - 1) {
                // Move Right
                neighbours[counter++] = board.getNewBoard(board.zx, board.zy, board.zx + 1, board.zy);
            }
            if (board.zx > 0) {
                // Move Left
                neighbours[counter++] = board.getNewBoard(board.zx, board.zy, board.zx - 1, board.zy);
            }

            if (board.zy < board.n - 1) {
                // Move Down
                neighbours[counter++] = board.getNewBoard(board.zx, board.zy, board.zx, board.zy + 1);
            }
            if (board.zy > 0) {
                // Move Up
                neighbours[counter++] = board.getNewBoard(board.zx, board.zy, board.zx, board.zy - 1);
            }
        }

        @Override
        public boolean hasNext() {
            return iterCount < counter;
        }

        @Override
        public Board next() {
            if (iterCount >= counter) throw new NoSuchElementException();
            return neighbours[iterCount++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit tests (not graded)
    public static void main(String[] args) {

        int[][] array1 = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        int[][] array2 = {{1, 2}, {3, 0}};

        Board[] boards = {new Board(array1), new Board(array2)};

        for (Board b : boards) {
            System.out.println(b);
            System.out.println("Hamming = " + b.hamming());
            System.out.println("Manhattan = " + b.manhattan());
//            board1.neighbors().forEach(b -> System.out.println(b));
            System.out.println("Is Goal = " + b.isGoal());
            System.out.println("************************************");
            System.out.println(b.twin());
        }


    }


}
