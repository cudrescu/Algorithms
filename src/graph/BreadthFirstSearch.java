package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class BreadthFirstSearch {

	static class Graph {

		List<Integer>[] adjancyList;

		public Graph(int nrNodes) {
			adjancyList = new ArrayList[nrNodes + 1];
			for(int i = 0; i <= nrNodes; i++) {
				adjancyList[i] = new ArrayList<>();
			}
		}

		public void addVertex(int i, int j) {
			adjancyList[i].add(j);
		}

	}

	private static void bfs(Graph g, int start) {
		Queue<Integer> queue = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();

		queue.add(start);
		while(!queue.isEmpty()) {
			int currentNode = queue.poll();
			System.out.println(currentNode + " ");
			for(Integer node : g.adjancyList[currentNode]) {
				if(!visited.contains(node)) {
					queue.add(node);
					visited.add(node);
				}
			}
		}
	}

	public static void main(String[] args) {

		Graph g = new Graph(5);

		g.addVertex(1, 4);
		g.addVertex(2, 3);
		g.addVertex(1, 3);
		g.addVertex(2, 4);
		g.addVertex(3, 5);

		bfs(g, 1);
	}

}
