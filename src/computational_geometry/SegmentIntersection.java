package computational_geometry;

public class SegmentIntersection {

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    /**
     * determine how p1p3 is relative to p1p2
     *
     * negative - counterclockwise
     * positive - clockwise
     * 0 - colinear
     *
     * @param p1
     * @param p2
     * @param p3
     * @return
     */
    private static int getDirection(Point p1, Point p2, Point p3) {
        //(p3 - p1) X (p2 - p1)
        // det  x1 x2
        //      y1 y2

        int x1 = p3.x - p1.x;
        int y1 = p3.y - p1.y;

        int x2 = p2.x - p1.x;
        int y2 = p2.y - p1.y;


        return x1 * y2 - x2 * y1;
    }

    public static boolean intersect(Point p1, Point p2, Point p3, Point p4) {

        int d1 = getDirection(p3, p4, p1);
        int d2 = getDirection(p3, p4, p2);
        int d3 = getDirection(p1, p2, p3);
        int d4 = getDirection(p1, p2, p4);

        if(struddlesLine(d1, d2) && struddlesLine(d3, d4)) {
            return true;
        }

        if(d1 == 0 && onSegment(p3, p4, p1)) {
            return true;
        }

        if(d2 == 0 && onSegment(p3, p4, p2)) {
            return true;
        }

        if(d3 == 0 && onSegment(p1, p2, p3)) {
            return true;
        }

        if(d4 == 0 && onSegment(p1, p2, p4)) {
            return true;
        }

        return false;
    }

    private static boolean onSegment(Point p3, Point p4, Point p1) {
        return p1.x >= Math.min(p3.x, p4.x) && p1.x <= Math.max(p3.x, p4.x) &&
                p1.y >= Math.min(p3.y, p4.y) && p1.y <= Math.max(p3.y, p4.y);
    }

    private static boolean struddlesLine(int d1, int d2) {
        return (d1 < 0 && d2 > 0) || (d1 > 0 && d2 < 0);
    }

    public static void main(String[] args) {

        Point p1 = new Point(2, 0);
        Point p2 = new Point(5, 5);

        Point p3 = new Point(1, 4);
        Point p4 = new Point(7, 5);

        System.out.println(intersect(p1, p2, p3 ,p4));
    }

}
