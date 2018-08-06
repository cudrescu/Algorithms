package data_structures;

public class OrderStatisticTree {

	static class TreeNode{

		int value;
		int size;

		TreeNode right;
		TreeNode left;
		TreeNode parent;

		public TreeNode(int value) {
			this.value = value;
		}

		public void insert(int value) {
			if(this.value >= value) {
				if(this.left == null) {
					this.left = new TreeNode(value);
					this.left.parent = this;
				} else {
					this.left.insert(value);
				}
			} else {
				if(this.right == null) {
					this.right = new TreeNode(value);
					this.right.parent = this;
				} else {
					this.right.insert(value);
				}
			}
		}

		public int computeSize() {
			int leftSize = 0;
			int rightSize = 0;

			if(this.left != null) {
				leftSize = this.left.computeSize();
			}
			if(this.right != null) {
				rightSize = this.right.computeSize();
			}

			this.size = 1 + leftSize + rightSize;

			return this.size;
		}

		@Override
		public String toString() {
			return "TreeNode{" + "value=" + value + ", size=" + size + '}';
		}
	}

	private static TreeNode createTree(int[] arr) {

		TreeNode root = new TreeNode(arr[0]);
		for(int i = 1; i < arr.length; i++) {
			root.insert(arr[i]);
		}

		return root;
	}

	//assumes it is first called with root as parameter
	private static TreeNode findKthElement(TreeNode node, int k) {

		if(node.left == null && node.right == null) {
			if(k == 1) {
				return node;
			} else {
				return null;
			}
		}

		if(node.left != null) {
			if(1 + node.left.size == k) {
				return node;
			}
			if(node.left.size < k) {
				if(node.right != null) {
					return findKthElement(node.right, k - node.left.size - 1);
				}
			}
			else {
				return findKthElement(node.left, k);
			}
		} else {
			return findKthElement(node.right, k);
		}

		return null;
	}

	public static void main(String[] args) {
		int[] input = new int[]{2, 1, 3, 1, 2};

		OrderStatisticTree.TreeNode root = createTree(input);

		root.computeSize();
		System.out.println("Tree size: " + root.size);

		System.out.println(findKthElement(root, 2));
		System.out.println(findKthElement(root, 5));
		System.out.println(findKthElement(root, 6));
	}

}
