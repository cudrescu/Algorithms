package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class KosarajuSCC {

	static class Graph {

		int n;
		int[][] adjancyMatrix;

		public Graph(int n, int[][] adjancyMatrix) {
			this.n = n;
			this.adjancyMatrix = adjancyMatrix;
		}
	}

	private static List<List<Integer>> stronglyConnectedComponents(Graph g) {

		Stack<Integer> vertices = new Stack<>();
		Set<Integer> visited = new HashSet<>();
		List<List<Integer>> result = new ArrayList<>();

		for(int vertex = 0; vertex < g.n; vertex++) {
			if(!visited.contains(vertex)) {
				dfs(g, vertex, vertices, visited);
			}
		}

		Graph h = reverseGraph(g);

		visited = new HashSet<>();
		while(!vertices.isEmpty()) {
			int i = vertices.pop();
			if(!visited.contains(i)) {
				List<Integer> component = new ArrayList<>();
				getComponent(h, i, visited, component);
				result.add(component);
			}
		}

		return result;
	}

	private static void getComponent(Graph g, int vertex, Set<Integer> visited, List<Integer> component) {
		visited.add(vertex);
		component.add(vertex);

		for(int i = 0; i < g.adjancyMatrix.length; i++) {
			if (g.adjancyMatrix[vertex][i] == 1 && !visited.contains(i)) {
				getComponent(g, i, visited, component);
			}
		}
	}

	private static Graph reverseGraph(Graph g) {
		Graph h = new Graph(g.n, new int[g.n][g.n]);
		for(int i = 0; i < g.n; i++) {
			for(int j = 0; j < g.n; j++) {
				h.adjancyMatrix[j][i] = g.adjancyMatrix[i][j];
			}
		}

		return h;
	}

	private static void dfs(Graph g, int vertex, Stack<Integer> vertices, Set<Integer> visited) {

		visited.add(vertex);

		for(int i = 0; i < g.adjancyMatrix.length; i++) {
			if(g.adjancyMatrix[vertex][i] == 1 && !visited.contains(i)) {
				dfs(g, i, vertices, visited);
			}
		}

		vertices.add(vertex);
	}

	public static void main(String[] args) {

		int[][] adjancyMatrix = new int[][] {
				{0, 1, 0, 0, 0, 0, 0},
				{0, 0, 1, 1, 0, 0, 0},
				{1, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 1, 0, 0},
				{0, 0, 0, 0, 0, 1, 0},
				{0, 0, 0, 1, 0, 0, 0},
				{0, 0, 0, 0, 0, 1, 0}
		};

		Graph g = new Graph(7, adjancyMatrix);
		List<List<Integer>> scc = stronglyConnectedComponents(g);

		for(List<Integer> component : scc) {
			for(Integer vertex : component) {
				System.out.print(vertex + " ");
			}
			System.out.println();
		}
	}
}
