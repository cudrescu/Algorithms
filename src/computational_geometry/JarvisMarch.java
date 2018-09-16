package computational_geometry;

import computational_geometry.SegmentIntersection.Point;

import java.util.*;

public class JarvisMarch {

    private static Set<Point> jarvis(List<Point> points) {

        Set<Point> result = new HashSet<>();

        points.sort(Comparator.comparingInt(p -> p.x));

        Point ref = points.get(0);
        Point start;
        result.add(ref);

        do {
            start = ref;
            for(Point p : points) {
                if(!result.contains(p) && !p.equals(ref)) {
                    if(validChoice(ref, p, points)) {
                        ref = p;
                        result.add(p);
                        break;
                    }
                }
            }

        } while(!ref.equals(start));

        return result;
    }

    private static boolean validChoice(Point ref, Point p, List<Point> points) {
        for(Point q : points) {
            if(!q.equals(ref)) {
                int direction = direction(ref, p, q);
                if(direction > 0) {
                    return false;
                }
                if(direction == 0) {
                    if(distance(ref, p) > distance(ref, q)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private static double distance(Point p1, Point p2) {
        return Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2);
    }

    private static int direction(Point ref, Point p, Point q) {
        int x1 = q.x - ref.x;
        int y1 = q.y - ref.y;

        int x2 = p.x - ref.x;
        int y2 = p.y - ref.y;

        return x1 * y2 - x2 * y1;
    }


    public static void main(String[] args) {

        List<Point> input = Arrays.asList(
                new Point(-2, 1),
                new Point(1, 1),
                new Point(4, 2),
                new Point(2, 2),
                new Point(4, 4),
                new Point(2, -1),
                new Point(4, -3),
                new Point(-1, -3)
        );

        Set<Point> jarvis = jarvis(input);

        System.out.println("Result: ");
        for(Point p : jarvis) {
            System.out.println(p);
        }
    }
}
