package computational_geometry;

import java.util.Arrays;

import computational_geometry.SegmentIntersection.Point;

public class ClosestPoints {

	static class Tuple {

		Point p1;
		Point p2;

		double distance;

		public Tuple(Point p1, Point p2, double distance) {
			this.p1 = p1;
			this.p2 = p2;
			this.distance = distance;
		}

		@Override
		public String toString() {
			return "Tuple{" + "p1=" + p1 + ", p2=" + p2 + ", distance=" + distance + '}';
		}
	}

	private static void closestPoints(Point[] points) {

		Arrays.sort(points, (p1, p2) -> {
			if(p1.x == p2.x) {
				return Integer.compare(p1.y, p2.y);
			}
			return Integer.compare(p1.x, p2.x);
		});

		Tuple tuple = divideAndConquer(points, 0, points.length - 1);

		System.out.println(tuple);
	}

	private static Tuple divideAndConquer(Point[] points, int left, int right) {

		if(right - left == 1) {
			return new Tuple(points[left], points[right], distance(points[left], points[right]));
		}

		if(right - left == 2) {
			double d1 = distance(points[left], points[right]);
			double d2 = distance(points[left], points[right - 1]);
			double d3 = distance(points[right - 1], points[right]);

			if(d1 < d2 && d1 < d3) {
				return new Tuple(points[left], points[right], d1);
			} else {
				if(d2 < d1 && d2 < d3) {
					return new Tuple(points[left], points[right - 1], d2);
				} else {
					return new Tuple(points[right - 1], points[right], d3);
				}
			}
		}

		int pivot = left + (right-left)/2;

		Tuple leftResult = divideAndConquer(points, left, pivot);
		Tuple rightResult = divideAndConquer(points, pivot, right);
		Tuple midResult = getMidResult(points, pivot, Math.min(leftResult.distance, rightResult.distance));

		return determineResult(leftResult, midResult, rightResult);
	}

	private static Tuple determineResult(Tuple leftResult, Tuple midResult, Tuple rightResult) {
		return null;
	}

	private static Tuple getMidResult(Point[] points, int pivot, double min) {
		/**
		 * go between p - min -> p + min and find out if there's another min
		 * tl:dr
		 */

		return null;
	}

	private static double distance(Point point, Point point1) {
		return 0;
	}

	public static void main(String[] args) {

	}

}
