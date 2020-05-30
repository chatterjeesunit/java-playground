package com.play.coursera.algorithmsOne.week3a.assignment;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * Created by sunitc on 6/11/17.
 */
public class FastCollinearPoints {
    private Point[] segmentPoints;
    private int count;
    private Point[] pointsCopy;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {

        if (points == null) throw new NullPointerException();
        segmentPoints = new Point[points.length];
        pointsCopy = new Point[points.length];
        count = 0;


        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new NullPointerException();
            pointsCopy[i] = points[i];
        }

        Arrays.sort(pointsCopy);
        for (int i = 0; i < pointsCopy.length; i++) {
            if (i > 0 && pointsCopy[i].compareTo(pointsCopy[i - 1]) == 0) throw new IllegalArgumentException();
        }

        for (int i = 0; i < pointsCopy.length; i++) {
            Arrays.sort(pointsCopy);
            getCollinear(pointsCopy[i], pointsCopy, i, pointsCopy.length);
        }
    }

    private void getCollinear(Point point, Point[] p, int lo, int hi) {
        if (hi - lo < 3) return;
        Arrays.sort(p, lo, hi, point.slopeOrder());
        int collinearPoints = 2; // since we always start with 2 points in comparison
        double previousSlope = point.slopeTo(p[lo]);
        int i = lo + 1;
        while (i < hi) {
            double newSlope = point.slopeTo(p[i]);
            if (Double.compare(previousSlope, newSlope) == 0) {
                collinearPoints++;
            } else {
                if (collinearPoints >= 4) {
                    addToLineSegments(point, p[i - 1]);
                }
                previousSlope = newSlope;
                collinearPoints = 2;
            }
            i++;
        }
        if (i == hi) {
            if (collinearPoints >= 4) {
                addToLineSegments(point, p[i - 1]);
            }
        }
    }

    private void addToLineSegments(Point start, Point end) {
        // Check if this segment points are already added as part of some other segments
        int i = 0;
        while (i < count * 2) {
            Point currStart = segmentPoints[i];
            Point currEnd = segmentPoints[i + 1];
            double slope1 = currStart.slopeTo(start);
            double slope2 = start.slopeTo(end);
            double slope3 = end.slopeTo(currEnd);

            if (
                    (currStart.equals(start) || Double.compare(slope1, slope2) == 0)
                            && (currEnd.equals(end) || Double.compare(slope2, slope3) == 0)) {
                return;
            }
            i = i + 2;
        }

        if (count == segmentPoints.length / 2) {
            // resize the lineSegments Array
            Point[] copy = new Point[segmentPoints.length * 2];
            for (int j = 0; j < segmentPoints.length; j++) {
                copy[j] = segmentPoints[j];
            }
            segmentPoints = copy;
        }

        segmentPoints[count * 2] = start;
        segmentPoints[count * 2 + 1] = end;
        count++;
    }


    // the number of line segments
    public int numberOfSegments() {
        return count;
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] segments = new LineSegment[count];
        for (int i = 0, j = 0; i < count * 2 && j < count; i = i + 2, j++) {
            LineSegment segment = new LineSegment(segmentPoints[i], segmentPoints[i + 1]);
            segments[j] = segment;
        }
        return segments;
    }


    public static void main(String[] args) {

        // read the n points from a file
        In in = new In("/datadrive/learn/Projects/java-pojo-apps/src/main/resources/coursera.algorithms1.collinear/rs1423.txt");
//        In in = new In(args[0]);
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
