package greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCode {

	static class TreeNode {

		char c;
		int freq;

		TreeNode left;
		TreeNode right;

		TreeNode() {}

		TreeNode(char c, int freq) {
			this.c = c;
			this.freq = freq;
		}

		@Override
		public String toString() {
			return "TreeNode{" + "c=" + c + ", freq=" + freq + '}';
		}
	}

	private static TreeNode huffman(Map<Character, Integer> input) {

		PriorityQueue<TreeNode> queue = new PriorityQueue<>((o1, o2) -> {
			if(o1.freq == o2.freq) {
				return 0;
			}
			return o1.freq > o2.freq ? 1:-1;
		});

		for(Map.Entry<Character, Integer> entry : input.entrySet()) {
			queue.add(new TreeNode(entry.getKey(), entry.getValue()));
		}

		for(int i = 0; i < input.size() - 1; i++) {
			TreeNode z = new TreeNode();

			TreeNode x = extractMin(queue);
			TreeNode y = extractMin(queue);

			z.freq = x.freq + y.freq;
			z.left = x;
			z.right = y;

			queue.add(z);
		}

		return extractMin(queue);
	}

	private static TreeNode extractMin(PriorityQueue<TreeNode> queue) {
		return queue.poll();
	}

	public static void main(String[] args) {

		Map<Character, Integer> input = new HashMap<>();
		input.put('f', 5);
		input.put('e', 9);
		input.put('c', 12);
		input.put('b', 13);
		input.put('d', 16);
		input.put('a', 45);

		System.out.println(huffman(input));
	}

}
