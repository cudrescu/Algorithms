package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DepthFirstSearch {

	static class Graph {

		List<Integer>[] adjancyList;

		public Graph(int nrNodes) {
			adjancyList = new ArrayList[nrNodes + 1];
			for(int i = 0; i <= nrNodes; i++) {
				adjancyList[i] = new ArrayList<>();
			}
		}

		public void addEdge(int i, int j) {
			adjancyList[i].add(j);
		}

	}

	private static void dfs(Graph g, int node, Set<Integer> visited) {
		System.out.println(node + " ");
		for(Integer nextNode : g.adjancyList[node]) {
			if(!visited.contains(nextNode)) {
				visited.add(nextNode);
				dfs(g, nextNode, visited);
			}
		}
	}

	public static void main(String[] args) {

		Graph g = new Graph(4);

		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		g.addEdge(2, 3);
		g.addEdge(3, 3);

		Set<Integer> visited = new HashSet<>();
		visited.add(2);
		dfs(g, 2, visited);
	}

}
