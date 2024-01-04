package datastructure;

import java.util.List;

/**
 * @Description:
 * @Author: whj
 * @Date: 2024-01-04 21:11
 */
public class KDTree {
    private KDTreeNode root;

    private static class KDTreeNode {
        Point point;
        KDTreeNode left;
        KDTreeNode right;

        KDTreeNode(Point point) {
            this.point = point;
        }
    }
    private class Point {

        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        double getX() {
            return x;
        }

        double getY() {
            return y;
        }


        static double distance(double x1, double x2, double y1, double y2) {
            return Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);
        }

        /**
         * Returns the euclidean distance (L2 norm) squared between two points.
         * Note: This is the square of the Euclidean distance, i.e.
         * there's no square root.
         */
        public static double distance(Point p1, Point p2) {
            return distance(p1.getX(), p2.getX(), p1.getY(), p2.getY());
        }

        @Override
        public boolean equals(Object other) {
            if (other == null) {
                return false;
            }
            if (other.getClass() != this.getClass()) {
                return false;
            }
            Point otherPoint = (Point) other;
            return getX() == otherPoint.getX() && getY() == otherPoint.getY();
        }

        @Override
        public int hashCode() {
            return Double.hashCode(x) ^ Double.hashCode(y);
        }

        @Override
        public String toString() {
            return String.format("Point x: %.10f, y: %.10f", x, y);
        }
    }


    public KDTree(List<Point> points) {
        for (Point point : points) {
            root = add(root, point, 'x');
        }
    }

    private KDTreeNode add(KDTreeNode cur, Point point, char axis) {
        if (cur == null) {
            return new KDTreeNode(point);
        }
        double diff = compareTwoPoint(cur.point, point, axis);
        if (diff <= 0) {
            cur.left = add(cur.left, point, exchange(axis));
        } else {
            cur.right = add(cur.right, point, exchange(axis));
        }
        return cur;
    }

    private static char exchange(char axis) {
        return axis == 'x' ? 'y' : 'x';
    }

    private static double compareTwoPoint(Point root, Point child, char axis) {
        if (axis == 'x') {
            return root.getX() - child.getX();
        } else {
            return root.getY() - child.getY();
        }
    }



    public Point nearest(double x, double y) {
        return findNearest(root, new Point(x, y), root, 'x').point;
    }

    private KDTreeNode findNearest(KDTreeNode cur, Point target, KDTreeNode best, char axis) {
        if (cur == null) {
            return best;
        }
        if (Point.distance(cur.point, target) < Point.distance(best.point, target)) {
            best = cur;
        }
        //delta x or delta y
        double diff = compareTwoPoint(cur.point, target, axis);
        KDTreeNode goodSide,badSide;
        if (diff <= 0) {
            goodSide = cur.left;
            badSide = cur.right;
        } else {
            goodSide = cur.right;
            badSide = cur.left;
        }
        best = findNearest(goodSide, target, best, exchange(axis));
        //Is there a point on the badSide located in a circle with radius r == Point.distance(best.point, target)
        if (diff * diff < Point.distance(best.point, target)) {
            best = findNearest(badSide, target, best, exchange(axis));
        }
        return best;
    }
}
