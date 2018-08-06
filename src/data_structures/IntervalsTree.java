package data_structures;

public class IntervalsTree {

	static class Interval {
		int start;
		int end;

		public Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return "Interval{" + "start=" + start + ", end=" + end + '}';
		}
	}

	static class TreeNode {

		Interval in;
		Interval intersect;
		int value;

		TreeNode left;
		TreeNode right;
		TreeNode parent;

		public TreeNode(Interval in) {
			this.in = in;
			this.value = in.start;
		}

		public void insertValue(Interval in) {
			if(this.value >= in.start) {
				if(this.left == null) {
					this.left = new TreeNode(in);
					this.left.parent = this;
				} else {
					this.left.insertValue(in);
				}
			} else {
				if(this.right == null) {
					this.right = new TreeNode(in);
					this.right.parent = this;
				} else {
					this.right.insertValue(in);
				}
			}
		}

		@Override
		public String toString() {
			return "TreeNode{" + "in=" + in + '}';
		}
	}

	public static void printIntervalTree(TreeNode node) {
		if(node == null) {
			return;
		}

		printIntervalTree(node.left);
		System.out.println(node);
		printIntervalTree(node.right);
	}

	private static int intersect(TreeNode node) {

		if(node == null) {
			return 0;
		}

		if(node.left == null && node.right == null) {
			node.intersect = node.in;
			return 1;
		}

		int leftIntersect = 0;
		int rightIntersect = 0;

		leftIntersect = intersect(node.left);
		rightIntersect = intersect(node.right);

		Interval refLeft = node.in;
		if(node.left != null) {
			refLeft = node.left.intersect;
			if (intervalsIntersect(node.left.intersect, node.in)) {
				refLeft = new Interval(Math.max(node.left.intersect.start, node.in.start), Math.min(node.left.intersect.end, node.in.end));
				leftIntersect++;
			}


		}

		Interval refRight = node.in;
		if(node.right != null) {
			refRight = node.right.intersect;
			if (rightIntersect != 0 && intervalsIntersect(node.in, node.right.intersect)) {
				refRight = new Interval(Math.max(node.right.intersect.start, node.in.start), Math.min(node.right.intersect.end, node.in.end));
				rightIntersect++;
			}
		}

		if(leftIntersect > rightIntersect) {
			node.intersect = refLeft;
			return leftIntersect;
		} else {
			node.intersect = refRight;
			return rightIntersect;
		}
	}

	private static boolean intervalsIntersect(Interval i1, Interval i2) {
		return i2.start <= i1.end;
	}

	public static void main(String[] args) {

		int[][] input = new int[][]{{0, 3}, {6, 10}, {5, 8}, {8, 9}, {17, 19}, {25, 30}, {16, 21}, {26, 26}, {19, 20}};

		TreeNode root = new TreeNode(new Interval(input[0][0], input[0][1]));
		for(int i = 1; i < input.length; i++) {
			root.insertValue(new Interval(input[i][0], input[i][1]));
		}

		printIntervalTree(root);

		System.out.println(intersect(root));
	}

}
