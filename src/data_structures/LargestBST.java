package data_structures;

public class LargestBST {

	static class BTreeNode {

		int value;

		BTreeNode left;
		BTreeNode right;

		public BTreeNode(int value) {
			this.value = value;
		}
	}

	static class Tuple {
		boolean isBST;
		int size;
		int minValue;
		int maxValue;

		public Tuple(boolean isBST, int size, int minValue, int maxValue) {
			this.isBST = isBST;
			this.size = size;
			this.minValue = minValue;
			this.maxValue = maxValue;
		}

		@Override
		public String toString() {
			return "Tuple{" + "isBST=" + isBST + ", size=" + size + ", minValue=" + minValue + ", maxValue=" + maxValue + '}';
		}
	}

	private static Tuple findLargestBST(BTreeNode node) {

		if(node == null) {
			return null;
		}

		Tuple left = findLargestBST(node.left);
		Tuple right = findLargestBST(node.right);

		if(left == null && right == null) {
			return new Tuple(true, 1, node.value, node.value);
		}

		if(left == null) {
			if(right.isBST) {
				if(node.value < right.minValue) {
					return new Tuple(true, 1 + right.size, node.value, right.maxValue);
				}
			}
			return new Tuple(false, 1, node.value, node.value);
		}

		if(right == null) {
			if(left.isBST) {
				if(node.value >= left.maxValue) {
					return new Tuple(true, 1 + left.size, left.minValue, node.value);
				}
			}
			return new Tuple(false, 1, node.value, node.value);
		}

		if(left.isBST && right.isBST) {
			if(node.value >= left.maxValue && node.value < right.minValue) {
				return new Tuple(true, 1 + left.size + right.size, left.minValue, right.maxValue);
			} else {
				return new Tuple(false, Math.max(left.size, right.size), 0, 0);
			}
		} else {
			return new Tuple(false, Math.max(left.size, right.size), 0, 0);
		}
	}

	public static void main(String[] args) {

		/**
		 * find largest BST in binary tree:
		 * 				  25
		 * 			  /	      \
		 * 			18           50
		 *		   /   \       /    \
		 *		 19    20     35     60
		 *		 \     / \    / \    /  \
		 *		 15   18 25  20 40  55  70
		 *		             \
		 *		             25
		 */

		BTreeNode node = new BTreeNode(25);
		node.left = new BTreeNode(18);
		node.right = new BTreeNode(50);

		BTreeNode n1 = node.left;
		n1.left = new BTreeNode(19);
		n1.right = new BTreeNode(20);

		n1.left.right = new BTreeNode(15);
		n1.right.left = new BTreeNode(18);
		n1.right.right = new BTreeNode(25);

		BTreeNode n2 = node.right;
		n2.left = new BTreeNode(35);
		n2.right = new BTreeNode(60);

		n2.left.left = new BTreeNode(20);
		n2.left.right = new BTreeNode(40);
		n2.right.left = new BTreeNode(55);
		n2.right.right = new BTreeNode(70);

		n2.left.left.right = new BTreeNode(25);

		System.out.println(findLargestBST(node));
	}
}
