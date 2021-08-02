package com.play.ds_algo.coursera.algorithms_1.week4a.assignment;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by sunitc on 6/18/17.
 */
public class Solver {

    private GameTree solutionTree;


    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null)
            throw new NullPointerException();
        solutionTree = new GameTree(initial);
    }


    // is the initial board solvable?
    public boolean isSolvable() {
        return solutionTree.isSolutionAvailable();
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (isSolvable())
            return solutionTree.getNumberOfMoves();

        return -1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (isSolvable()) {
            return solutionTree;
        }

        return null;
    }

    // solve a slider puzzle (given below)
    public static void main(String[] args) {

        final String file = Solver.class.getClassLoader().getResource("coursera/algorithms1/8puzzle/puzzle07.txt").getFile();
//        // create initial board from file
        In in = new In(file);
//        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable()) {
            StdOut.println("No solution possible");
        } else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }

    }


    private class BoardNode implements Comparable<BoardNode> {
        private Board board;
        private int moveNum;
//        private MinPQ<BoardNode> minPQ;

        public BoardNode(Board board, int moveNum) {
//            minPQ = new MinPQ<>();
            this.board = board;
            this.moveNum = moveNum;
        }

//        public MinPQ<BoardNode> getMinPQ() {
//            return minPQ;
//        }

        public int getMoveNum() {
            return moveNum;
        }

        public Board getBoard() {
            return board;
        }

        public int getHammingPriority() {
            return board.hamming() + moveNum;
        }

        public int getManhattanPriority() {
            return board.manhattan() + moveNum;
        }

        @Override
        public int compareTo(BoardNode o) {
            if (o == null) return 1;
            if (o == this) return 0;
//        int movesCompare = Integer.compare(this.moveNum, o.moveNum);
//        if(movesCompare == 0) {
            int hammingCompare = Integer.compare(this.getHammingPriority(), o.getHammingPriority());
//            if (hammingCompare == 0) {
//                return Integer.compare(this.getManhattanPriority(), o.getManhattanPriority());
//            } else {
                return hammingCompare;
//            }
//        }
//        return movesCompare;


        }

        public String toString() {
            StringBuilder builder = new StringBuilder(this.getBoard().toString());
            builder.append("Moves = ").append(this.getMoveNum()).append("; ");
            builder.append("Hamming Priority = ").append(this.getHammingPriority()).append("; ");
            builder.append("Manhattan Priority = ").append(this.getManhattanPriority()).append("; ");
            return builder.toString();
        }
    }

    private class GameTree implements Iterable<Board> {
        private int currentIndex = 0;
        private BoardNode[] boardNodes;

        public GameTree(Board initial) {
            boardNodes = new BoardNode[1];
            findSolution(initial);
        }

        private void findSolution(Board initial) {
            MinPQ<BoardNode> minPQ = addBoard(initial);
            BoardNode currentNode = boardNodes[currentIndex - 1];
            while (!currentNode.getBoard().isGoal()) {

                if (minPQ.isEmpty()) {
                    // No new neighbours added, then break, as no more solution is possible
                    break;
                } else {
                    BoardNode minBoardNode = minPQ.delMin();
                    minPQ = addBoard(minBoardNode.getBoard());
                    currentNode = boardNodes[currentIndex - 1];
                }
            }
        }

        private MinPQ<BoardNode> addBoard(Board board) {

            // Resize if limit reached
            if (currentIndex == boardNodes.length) {
                BoardNode[] copy = new BoardNode[boardNodes.length * 2];
                for (int i = 0; i < currentIndex; i++) {
                    copy[i] = boardNodes[i];
                }
                boardNodes = copy;
            }

            // Create a new board node
            BoardNode newBoardNode = new BoardNode(board, currentIndex);
            // Create a MinPQ of all neighbours of this node (not already added)
            MinPQ<BoardNode> minPQ = new MinPQ<>();
            newBoardNode.getBoard().neighbors().forEach(n -> {
                BoardNode neighbour = new BoardNode(n, currentIndex + 1);
                if (this.canAddToMinPQ(neighbour)) {
                    minPQ.insert(neighbour);
                }
            });
            boardNodes[currentIndex] = newBoardNode;
//        StdOut.println(boardNodes[currentIndex]);
            currentIndex++;
            return minPQ;
        }

        private boolean canAddToMinPQ(BoardNode boardNode) {
            // check if solution tree already contains this board
            for (int i = 0; i < currentIndex; i++) {
                if (boardNodes[i].getBoard().equals(boardNode.getBoard()))
                    return false;
            }
            return true;
        }

        public boolean isSolutionAvailable() {
            return boardNodes[currentIndex - 1].getBoard().isGoal();
        }

        public int getNumberOfMoves() {
            return boardNodes[currentIndex - 1].getMoveNum();
        }

        @Override
        public Iterator<Board> iterator() {
            return new GameTreeIterator();
        }

        private class GameTreeIterator implements Iterator<Board> {

            private int counter = 0;

            @Override
            public boolean hasNext() {
                return counter < currentIndex;
            }

            @Override
            public Board next() {
                if (counter >= currentIndex) throw new NoSuchElementException();
                return boardNodes[counter++].getBoard();
            }
        }
    }

}

