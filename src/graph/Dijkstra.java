package graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Dijkstra {

	static class Graph {

		int nrOfVertices;
		int[][] weights;

		public Graph(int nrOfVertices, int[][] weights) {
			this.nrOfVertices = nrOfVertices;
			this.weights = weights;
		}
	}

	private static void dijkstra(Graph g, int source) {

		Set<Integer> selected = new HashSet<>();
		int[] distance = new int[g.nrOfVertices];
		int[] parent = new int[g.nrOfVertices];

		for(int i = 0; i < g.nrOfVertices; i++) {
			distance[i] = Integer.MAX_VALUE;
		}

		distance[source] = 0;
		parent[source] = -1;

		for(int i = 0; i < g.nrOfVertices; i++) {
			int vertex = selectMin(distance, selected);
			selected.add(vertex);

			for(int j = 0; j < g.nrOfVertices; j++) {
				if(g.weights[vertex][j] != 0) {
					if(distance[j] >  distance[vertex] + g.weights[vertex][j]) {
						distance[j] = distance[vertex] + g.weights[vertex][j];
						parent[j] = vertex;
					}
				}
			}
		}

		System.out.println(Arrays.toString(distance));
		System.out.println(Arrays.toString(parent));
	}

	private static int selectMin(int[] distance, Set<Integer> selected) {

		int min = Integer.MAX_VALUE;
		int minIndex = -1;
		for(int i = 0; i < distance.length; i++) {
			if(distance[i] < min && !selected.contains(i)) {
				min = distance[i];
				minIndex = i;
			}
		}

		return minIndex;
	}

	public static void main(String[] args) {

		int[][] weights = new int[][]{{0, 4, 0, 0, 0, 0, 0, 8, 0},
				{4, 0, 8, 0, 0, 0, 0, 11, 0},
				{0, 8, 0, 7, 0, 4, 0, 0, 2},
				{0, 0, 7, 0, 9, 14, 0, 0, 0},
				{0, 0, 0, 9, 0, 10, 0, 0, 0},
				{0, 0, 4, 14, 10, 0, 2, 0, 0},
				{0, 0, 0, 0, 0, 2, 0, 1, 6},
				{8, 11, 0, 0, 0, 0, 1, 0, 7},
				{0, 0, 2, 0, 0, 0, 6, 7, 0}
		};

		Graph g = new Graph(weights.length, weights);

		dijkstra(g, 0);
	}
}
