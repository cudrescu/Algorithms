package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KruskalMST {

	static class Node {
		int rank;
		int vertex;

		Node parent;
	}

	static class DisjointSet {

		Node makeSet(int vertex) {
			Node node = new Node();
			node.vertex = vertex;
			node.rank = 0;
			node.parent = node;

			return node;
		}

		void union(Node n1, Node n2) {
			if(n1.rank >= n2.rank) {
				n2.parent = n1;
				n1.rank ++;
			} else {
				n1.parent = n2;
				n2.rank ++;
			}
		}

		Node getSet(Node n) {
			Node parent = n.parent;
			while(parent != n) {
				n = n.parent;
				parent = n.parent;
			}

			return parent;
		}
	}

	static class Edge {
		int v1;
		int v2;
		int cost;

		public Edge(int v1, int v2, int cost) {
			this.v1 = v1;
			this.v2 = v2;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "Edge{" + "v1=" + v1 + ", v2=" + v2 + ", cost=" + cost + '}';
		}
	}

	private static List<Edge> kruskalMST(Edge[] edges, int nOfVertices) {

		Arrays.sort(edges, Comparator.comparingInt(e -> e.cost));

		DisjointSet dSet = new DisjointSet();
		List<Edge> result = new ArrayList<>();
		Map<Integer, Node> verticesMap = new HashMap<>();
		for(int i = 1; i <= nOfVertices; i++) {
			verticesMap.put(i, dSet.makeSet(i));
		}

		for(Edge e : edges) {
			if(dSet.getSet(verticesMap.get(e.v1)) != dSet.getSet(verticesMap.get(e.v2))) {
				result.add(e);
				dSet.union(dSet.getSet(verticesMap.get(e.v1)), dSet.getSet(verticesMap.get(e.v2)));
			}
		}

		return result;
	}

	public static void main(String[] args) {

		Edge[] input = new Edge[]{
			new Edge(1, 4, 1),
			new Edge(1, 2, 3),
			new Edge(2, 4, 3),
			new Edge(2, 3, 1),
			new Edge(4, 3, 1),
			new Edge(4, 5, 6),
			new Edge(3, 5, 5),
			new Edge(3, 6, 4),
			new Edge(5, 6, 2)
		};

		List<Edge> kruskalMST = kruskalMST(input, 6);
		kruskalMST.forEach(System.out::println);
	}
}
