package com.play.coursera_algorithms_1.week3a.assignment;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * Created by sunitc on 6/11/17.
 */
public class BruteCollinearPoints {
    private LineSegment[] lineSegments;
    private int count;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new NullPointerException();
        lineSegments = new LineSegment[points.length];
        count = 0;
        Point[] pointsCopy = new Point[points.length];

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new NullPointerException();
            pointsCopy[i] = points[i];
        }

        Arrays.sort(pointsCopy);
        for (int i = 0; i < pointsCopy.length; i++) {
            if (i > 0 && pointsCopy[i].compareTo(pointsCopy[i - 1]) == 0) throw new IllegalArgumentException();
        }

        for (int i = 0; i < pointsCopy.length; i++) {
            for (int j = i + 1; j < pointsCopy.length; j++) {
                for (int k = j + 1; k < pointsCopy.length; k++) {
                    for (int l = k + 1; l < pointsCopy.length; l++) {
                        double slope1 = pointsCopy[i].slopeTo(pointsCopy[j]);
                        double slope2 = pointsCopy[i].slopeTo(pointsCopy[k]);
                        double slope3 = pointsCopy[i].slopeTo(pointsCopy[l]);

                        if (Double.compare(slope1, slope2) == 0 && Double.compare(slope2, slope3) == 0) {
                            addToLineSegments(new LineSegment(pointsCopy[i], pointsCopy[l]));
                        }
                    }
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return count;
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] copy = new LineSegment[count];
        for (int i = 0; i < count; i++) {
            copy[i] = lineSegments[i];
        }
        return copy;
    }

    private void addToLineSegments(LineSegment segment) {
        if (count == lineSegments.length) {
            // resize the lineSegments Array
            LineSegment[] copy = new LineSegment[lineSegments.length * 2];
            for (int i = 0; i < count; i++) {
                copy[i] = lineSegments[i];
            }
            lineSegments = copy;
        }
        lineSegments[count++] = segment;
    }


    public static void main(String[] args) {

        final String file = BruteCollinearPoints.class.getClassLoader().getResource("coursera/algorithms1/collinear/input20.txt").getFile();
        // read the n points from a file
        In in = new In(file);
        // read the n points from a file
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }


}
