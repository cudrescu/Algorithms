package computational_geometry;

import computational_geometry.SegmentIntersection.Point;

import java.util.*;

import static computational_geometry.SegmentIntersection.intersect;

public class AnySegmentIntersect {

    static class Segment {

        Point left;
        Point right;

        public Segment(Point left, Point right) {
            this.left = left;
            this.right = right;
        }
    }


    /**
     * computes distance from point p to segment ab;
     * 
     * @param p
     * @param a
     * @param b
     * @return
     */
    private static double pDistance(Point p, Point a, Point b) {

        int A = p.x - a.x;
        int B = p.y - a.y;
        int C = b.x - a.x;
        int D = b.y - a.y;

        int dot = A * C + B * D;
        int len_sq = C * C + D * D;
        int param = -1;
        if (len_sq != 0) //in case of 0 length line
            param = dot / len_sq;

        int xx, yy;

        if (param < 0) {
            xx = a.x;
            yy = a.y;
        }
        else if (param > 1) {
            xx = b.x;
            yy = b.y;
        }
        else {
            xx = a.x + param * C;
            yy = a.y + param * D;
        }

        int dx = p.x - xx;
        int dy = p.y - yy;

        return Math.sqrt(dx * dx + dy * dy);
    }


    private static boolean sweepLine(List<Point> points, List<Segment> segments) {
        points.sort(Comparator.comparingInt(p -> p.x));

        Set<Segment> visited = new HashSet<>();

        for(Point p : points) {

            Segment segment = findSegment(segments, p);

            if(p.equals(segment.left)) {
                visited.add(segment);
            } else {
                visited.remove(segment);
                Segment closestSegment = findClosestSegment(p, visited);

                if(closestSegment != null && intersect(segment.left, segment.right, closestSegment.left, closestSegment.right)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static Segment findSegment(List<Segment> segments, Point p) {
        for(Segment s : segments) {
            if(s.left.equals(p) || s.right.equals(p)) {
                return s;
            }
        }

        return null;
    }

    private static Segment findClosestSegment(Point p, Set<Segment> visited) {

        double min = Double.MAX_VALUE;
        Segment result = null;

        for(Segment s : visited) {
            double distance = pDistance(p, s.left, s.right);
            if(distance < min) {
                min = distance;
                result = s;
            }
        }

        return result;
    }

    public static void main(String[] args) {

        Point p1 = new Point(2, 0);
        Point p2 = new Point(5, 5);
        Point p3 = new Point(2, 3);
        Point p4 = new Point(6, 7);
        Point p5 = new Point(0, 5);
        Point p6 = new Point(2, 5);
        Point p7 = new Point(1, 4);
        Point p8 = new Point(7, 5);

        Segment s1 = new Segment(p1, p2);
        Segment s2 = new Segment(p3, p4);
        Segment s3 = new Segment(p5, p6);
        Segment s4 = new Segment(p7, p8);

        System.out.println(sweepLine(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8), Arrays.asList(s1,s2,s3,s4)));
    }

}
