package com.play.coursera_algorithms_1.week5b.assignment;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.TreeSet;

/**
 * Created by sunitc on 7/2/17.
 */
public class PointSET {

    private final TreeSet<Point2D> points;

    // construct an empty set of points
    public PointSET() {
        points = new TreeSet<>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return points.isEmpty();
    }

    // number of points in the set
    public int size() {
        return points.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        points.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        return points.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        for (Point2D point : points) {
            StdDraw.point(point.x(), point.y());
        }
    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        Queue<Point2D> queue = new Queue<>();
        points.forEach(p -> {
            if (rect.contains(p)) queue.enqueue(p);
        });
        return queue;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (isEmpty()) return null;
        Point2D nearestPoint = null;
        double distance = -1;
        for (Point2D point : points) {
            double newDistance = p.distanceSquaredTo(point);
            if (nearestPoint == null) {
                nearestPoint = point;
                distance = newDistance;
            } else if (Double.compare(newDistance, distance) < 0) {
                nearestPoint = point;
                distance = newDistance;
            }
        }
        return nearestPoint;

    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {

        PointSET set = new PointSET();
        set.insert(new Point2D(0.7, 0.2));
        set.insert(new Point2D(0.5, 0.4));
        set.insert(new Point2D(0.2, 0.3));
        set.insert(new Point2D(0.4, 0.7));
        set.insert(new Point2D(0.9, 0.6));
        set.insert(new Point2D(0.65, 0.65));
        set.insert(new Point2D(0.3, 0.9));
        set.insert(new Point2D(0.05, 0.55));
        set.insert(new Point2D(0.25, 0.35));
        set.insert(new Point2D(0.25, 0.35));
        set.insert(new Point2D(0.25, 0.35));
        set.insert(new Point2D(0.15, 0.35));

        StdDraw.enableDoubleBuffering();
        StdDraw.setPenRadius(0.01);
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);
        set.draw();


        RectHV rectHV = new RectHV(0.15, 0.25, 0.65, 0.6);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(0.005);
        rectHV.draw();

        set.range(rectHV).forEach(p -> {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.setPenRadius(0.01);
            StdDraw.point(p.x(), p.y());
        });

        Point2D otherPoint = new Point2D(0.35, 0.37);
        Point2D nearestPoint = set.nearest(otherPoint);
        StdDraw.setPenRadius(0.003);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.line(otherPoint.x(), otherPoint.y(), nearestPoint.x(), nearestPoint.y());

        StdDraw.show();
    }
}