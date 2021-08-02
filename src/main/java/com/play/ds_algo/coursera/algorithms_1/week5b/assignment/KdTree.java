package com.play.ds_algo.coursera.algorithms_1.week5b.assignment;

import edu.princeton.cs.algs4.*;

import java.util.Iterator;
import java.util.Optional;

/**
 * Created by sunitc on 7/2/17.
 */
public class KdTree {

    private static final double POINT_RADIUS = 0.01;
    private static final double LINE_RADIUS = 0.005;
    private static final double RANGE_RADIUS = 0.025;
    private static final double NEIGHBOUR_LINE_RADIUS = 0.003;
    private KdTreeNode rootNode;

    // construct an empty set of points
    public KdTree() {
        rootNode = null;
    }

    // is the set empty?
    public boolean isEmpty() {
        return rootNode == null;
    }

    // number of points in the set
    public int size() {
        return Optional.ofNullable(rootNode).map(r -> r.numNodes).orElse(0);
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (rootNode == null) {
            rootNode = new KdTreeNode(p, true, null, null);
        } else {
            rootNode.addNewPoint(p);
        }
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        return rootNode.find(p) != null;
    }

    // draw all points to standard draw
    public void draw() {
        if (isEmpty()) return;
        draw(rootNode, 0, 1, 0, 1);
    }

    private void draw(KdTreeNode node, double xMin, double xMax, double yMin, double yMax) {

        if (node == null) return;
        Point2D point = node.getPoint();
        StdDraw.setPenRadius(POINT_RADIUS);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.point(point.x(), point.y());
        if (!node.isVertical()) {
            StdDraw.setPenRadius(LINE_RADIUS);
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(xMin, point.y(), xMax, point.y());
            draw(node.getLeftChild(), xMin, xMax, yMin, point.y());
            draw(node.getRightChild(), xMin, xMax, point.y(), yMax);
        } else {
            StdDraw.setPenRadius(LINE_RADIUS);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(point.x(), yMin, point.x(), yMax);
            draw(node.getLeftChild(), xMin, point.x(), yMin, yMax);
            draw(node.getRightChild(), point.x(), xMax, yMin, yMax);
        }

    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        return new PointsIterable(rect);
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (isEmpty()) return null;
        Point2D nearestPoint = null;
        double distance = -1;
        Iterator<KdTreeNode> iterator = rootNode.iterator();
        while (iterator.hasNext()) {
            Point2D point = iterator.next().getPoint();
            double newDistance = p.distanceTo(point);
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

    private class PointsIterable implements Iterable<Point2D> {

        private final RectHV rect;
        private final Queue<Point2D> rectPoints;

        public PointsIterable(RectHV rect) {
            this.rectPoints = new Queue<>();
            this.rect = rect;
        }

        @Override
        public Iterator<Point2D> iterator() {
            rootNode.iterator().forEachRemaining(n -> {
                if (rect.contains(n.getPoint())) rectPoints.enqueue(n.getPoint());
                return;
            });
            return rectPoints.iterator();
        }
    }

    private class KdTreeNode implements Comparable<Point2D>, Iterable<KdTreeNode> {
        private Point2D point;
        private boolean vertical = true;
        private KdTreeNode leftChild;
        private KdTreeNode rightChild;
        private int numNodes = 0;

        public KdTreeNode(Point2D point, boolean isVertical, KdTreeNode leftChild, KdTreeNode rightChild) {
            this.point = point;
            this.vertical = isVertical;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.numNodes = 1;
        }

        public Point2D getPoint() {
            return point;
        }

        public void setPoint(Point2D point) {
            this.point = point;
        }

        public boolean isVertical() {
            return vertical;
        }

        public void setVertical(boolean vertical) {
            this.vertical = vertical;
        }

        public KdTreeNode getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(KdTreeNode leftChild) {
            this.leftChild = leftChild;
            setNumNodes();
        }

        public KdTreeNode getRightChild() {
            return rightChild;
        }

        public void setRightChild(KdTreeNode rightChild) {
            this.rightChild = rightChild;
            setNumNodes();
        }

        private void setNumNodes() {
            int leftNodes = Optional.ofNullable(this.getLeftChild()).map(left -> left.numNodes).orElse(0);
            int rightNodes = Optional.ofNullable(this.getRightChild()).map(right -> right.numNodes).orElse(0);
            this.numNodes = leftNodes + rightNodes + 1;
        }


        @Override
        public int compareTo(Point2D otherPoint) {
            if (otherPoint == null) return 1;
            if (this.getPoint() == otherPoint) return 0;
            if (this.getPoint().compareTo(otherPoint) == 0) return 0;
            else {
                if (this.isVertical()) {
                    // Compare only x co-ordinates
                    int compare = Double.compare(this.getPoint().x(), otherPoint.x());
                    if (compare == 0) {
                        return Double.compare(this.point.y(), otherPoint.y());
                    } else return compare;
                } else {
                    // Compare only y co-ordinates
                    int compare = Double.compare(this.getPoint().y(), otherPoint.y());
                    if (compare == 0) {
                        return Double.compare(this.point.x(), otherPoint.x());
                    } else return compare;
                }
            }
        }

        @Override
        public Iterator<KdTreeNode> iterator() {
            return new KdTreeNodeIterator(rootNode).getIterator();
        }


        private class KdTreeNodeIterator {
            private final Queue<KdTreeNode> queue = new Queue<>();

            KdTreeNodeIterator(KdTreeNode node) {
                addToQueue(node);
            }


            private void addToQueue(KdTreeNode node) {
                if (node.getLeftChild() != null) addToQueue(node.getLeftChild());
                queue.enqueue(node);
                if (node.getRightChild() != null) addToQueue(node.getRightChild());
            }

            private Iterator<KdTreeNode> getIterator() {
                return queue.iterator();
            }
        }


        public KdTreeNode addNewPoint(Point2D newPoint) {
            int compare = this.compareTo(newPoint);
            if (compare < 0) {
                if (this.getRightChild() == null) {
                    this.setRightChild(new KdTreeNode(newPoint, !this.isVertical(), null, null));
                } else {
                    this.setRightChild(this.getRightChild().addNewPoint(newPoint));
                }

            } else if (compare > 0) {
                if (this.getLeftChild() == null) {
                    this.setLeftChild(new KdTreeNode(newPoint, !this.isVertical(), null, null));
                } else {
                    this.setLeftChild(this.getLeftChild().addNewPoint(newPoint));
                }
            }
            return this;
        }

        public KdTreeNode find(Point2D pointToFind) {
            if (pointToFind == null) throw new IllegalArgumentException();
            int compare = this.compareTo(pointToFind);
            if (compare > 0 && this.getLeftChild() != null) {
                return this.getLeftChild().find(pointToFind);

            } else if (compare < 0 && this.getRightChild() != null) {
                return this.getRightChild().find(pointToFind);
            } else if (compare == 0) return this;
            return null;
        }

        @Override
        public String toString() {
            return "(" + this.getPoint().x() + ", " + this.getPoint().y() + ", " + this.isVertical() + ")";
        }
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {

//        com.play.algorithms1.week5b.assignment.KdTree set = new com.play.algorithms1.week5b.assignment.KdTree();
//        set.insert(new Point2D(0.7, 0.2));
//        set.insert(new Point2D(0.5, 0.4));
//        set.insert(new Point2D(0.2, 0.3));
//        set.insert(new Point2D(0.4, 0.7));
//        set.insert(new Point2D(0.9, 0.6));
//        set.insert(new Point2D(0.65, 0.65));
//        set.insert(new Point2D(0.3, 0.9));
//        set.insert(new Point2D(0.05, 0.55));
//        set.insert(new Point2D(0.25, 0.35));
//        set.insert(new Point2D(0.25, 0.35));
//        set.insert(new Point2D(0.25, 0.35));
//        set.insert(new Point2D(0.25, 0.35));
//        set.insert(new Point2D(0.65, 0.65));
//        set.insert(new Point2D(0.7, 0.2));
//        set.insert(new Point2D(0.4, 0.7));
//
//        System.out.println(set.size());
//
//        System.out.println(set.contains(new Point2D(0.25, 0.35)));
//        System.out.println(set.contains(new Point2D(0.25, 0.36)));
//        System.out.println(set.contains(new Point2D(0.71, 0.36)));
//        System.out.println(set.contains(new Point2D(0.4, 0.7)));
//
//        StdDraw.enableDoubleBuffering();
//        StdDraw.setPenRadius(0.01);
//        StdDraw.setXscale(0, 1);
//        StdDraw.setYscale(0, 1);
//        set.draw();
//
//
//        RectHV rectHV = new RectHV(0.15, 0.25, 0.65, 0.6);
//        StdDraw.setPenColor(StdDraw.BOOK_RED);
//        StdDraw.setPenRadius(LINE_RADIUS);
//        rectHV.draw();
//
//        set.range(rectHV).forEach(p -> {
//            StdDraw.setPenColor(StdDraw.GREEN);
//            StdDraw.setPenRadius(RANGE_RADIUS);
//            StdDraw.point(p.x(), p.y());
//        });
//
//        Point2D otherPoint = new Point2D(0.35, 0.37);
//        Point2D nearestPoint = set.nearest(otherPoint);
//        StdDraw.setPenRadius(NEIGHBOUR_LINE_RADIUS);
//        StdDraw.setPenColor(StdDraw.BLACK);
//        StdDraw.line(otherPoint.x(), otherPoint.y(), nearestPoint.x(), nearestPoint.y());
//
//        StdDraw.show();

        final String file = KdTree.class.getClassLoader().getResource("coursera/algorithms1/kdtree/input.txt").getFile();
        In in = new In(file);
        // initialize the data structures with N points from standard input
        KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
        }

        System.out.println(kdtree.size());
        System.out.println(kdtree.contains(new Point2D(0.776449, 0.848032)));

    }
}
