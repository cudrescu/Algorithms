package computational_geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class SkylineProblem {

	static class Point {

		int x;
		int y;
		boolean start;

		public Point(int x, int y, boolean start) {
			this.x = x;
			this.y = y;
			this.start = start;
		}

		@Override
		public String toString() {
			return "Point{" + "x=" + x + ", y=" + y + ", start=" + start + '}';
		}
	}

	private static List<Point> getSkylinePoints(Point[] points) {
		Arrays.sort(points, (p1, p2) -> {
			if(p1.x == p2.x) {
				if (p1.y == p2.y) {
					return 0;
				}
				if ((p1.start ^ p2.start) || (p1.start && p2.start)) {
					return p1.y > p2.y ? -1 : 1;
				} else {
					return p1.y > p2.y ? 1 : -1;
				}
			}
			return Integer.compare(p1.x, p2.x);
		});

		List<Point> result = new ArrayList<>();
		Queue<Integer> heights = new PriorityQueue<>(Comparator.reverseOrder());
		heights.add(0);

		for(Point point : points) {
			int currentTop = heights.peek();

			if(point.start && point.y > currentTop) {
				result.add(point);
				heights.add(point.y);
			}

			if(!point.start) {
				heights.remove(point.y);
				if(heights.peek() != currentTop) {
					result.add(new Point(point.x, heights.peek(), false));
				}
			}
		}

		return result;
	}

	public static void main(String[] args) {

		Point[] input = new Point[] {
				new Point(1, 3, true),
				new Point(2, 4, true),
				new Point(3, 3, false),
				new Point(4, 4, false),
				new Point(5, 2, true),
				new Point(6, 4, true),
				new Point(7, 4, false),
				new Point(8, 4, true),
				new Point(8, 2, false),
				new Point(9, 4, false),
				new Point(1, 2, true),
				new Point(3, 2, false),
		};

		getSkylinePoints(input).forEach(System.out::println);
	}

}
