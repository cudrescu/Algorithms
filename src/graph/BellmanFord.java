package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BellmanFord {

	static class Edge {
		int from;
		int to;
		int weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}

	static class Graph {

		int nrOfVertices;
		List<Edge> edges;

		public Graph(int nrOfVertices) {
			this.nrOfVertices = nrOfVertices;
			this.edges = new ArrayList<>();
		}

		public void addEdge(int from, int to, int weight) {
			this.edges.add(new Edge(from, to, weight));
			this.edges.add(new Edge(to, from, weight));
		}
	}

	private static boolean bellmanFord(Graph g, int source) {

		int[] distance = new int[g.nrOfVertices];
		for(int i = 0; i < g.nrOfVertices; i++) {
			distance[i] = Integer.MAX_VALUE;
		}

		distance[source] = 0;

		for(int i = 0; i < g.nrOfVertices; i++) {
			for(Edge edge : g.edges) {
				if(distance[edge.from] != Integer.MAX_VALUE) {
					if (distance[edge.to] > distance[edge.from] + edge.weight) {
						distance[edge.to] = distance[edge.from] + edge.weight;
					}
				}
			}
		}

		for(Edge edge : g.edges) {
			if(distance[edge.to] > distance[edge.from] + edge.weight) {
				return false;
			}
		}

		System.out.println(Arrays.toString(distance));

		return true;
	}

	/* Let us create the following graph
           2    3
        (0)--(1)--(2)
        |    / \   |
        6| 8/   \5 |7
        | /      \ |
        (3)-------(4)
             9          */
	public static void main(String[] args) {

		Graph g = new Graph(5);
		g.addEdge(0, 1, 2);
		g.addEdge(1, 2, 3);
		g.addEdge(0, 3, 6);
		g.addEdge(1, 3, 8);
		g.addEdge(3, 4, 9);
		g.addEdge(1, 4, 5);
		g.addEdge(2, 4, 7);

		System.out.println(bellmanFord(g, 3));
	}
}
