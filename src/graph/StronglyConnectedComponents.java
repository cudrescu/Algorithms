package graph;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class StronglyConnectedComponents {

	static class Vertex {
		int id;
		long f;
		Vertex parent;

		public Vertex(int id) {
			this.id = id;
		}
	}

	static class Graph {

		int nrOfVertices;
		Set<Vertex>[] adjancyList;

		public Graph(int nrOfVertices) {
			this.nrOfVertices = nrOfVertices;
			adjancyList = new LinkedHashSet[nrOfVertices];
			for(int i = 0; i < nrOfVertices; i++) {
				adjancyList[i] = new LinkedHashSet<>();
			}
		}

		public void addEdge(int i, int j) {
			adjancyList[i].add(new Vertex(j));
		}
	}

	private static void dfs(Graph g, Vertex v, Set<Vertex> visited) {

		for(Vertex vertex : g.adjancyList[v.id]) {
			if(!visited.contains(vertex)) {
				visited.add(vertex);
				dfs(g, vertex, visited);
			}
		}

		v.f = System.currentTimeMillis();
	}

	private static void computeConnexTree(Graph g, Vertex v, Set<Vertex> visited) {

		LinkedHashSet<Vertex> adj = (LinkedHashSet<Vertex>) g.adjancyList[v.id];
		Vertex[] aux = adj.toArray(new Vertex[0]);
		Arrays.sort(aux, new Comparator<Vertex>() {
			@Override
			public int compare(Vertex o1, Vertex o2) {
				if(o1.f == o2.f) {
					return 0;
				}
				return o1.f > o2.f ? -1 : 1;
			}
		});

		for(Vertex vertex : aux) {
			if(!visited.contains(vertex)) {
				visited.add(vertex);
				vertex.parent = v;
				dfs(g, vertex, visited);
			}
		}
	}

	private static Graph transpose(Graph g) {

		Graph h = new Graph(g.nrOfVertices);

		for(int i = 0; i < g.nrOfVertices; i++) {
			for(Vertex vertex : g.adjancyList[i]) {
				h.adjancyList[vertex.id].add(new Vertex(i));
			}
		}

		return h;
	}

	public static void main(String[] args) {

		Graph g = new Graph(4);

		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		g.addEdge(2, 3);
		g.addEdge(3, 3);

		Set<Vertex> visited = new HashSet<>();
		Vertex start = new Vertex(2);
		visited.add(start);
		dfs(g, start, visited);

		Graph h = transpose(g);

		visited = new HashSet<>();
		visited.add(start);
		computeConnexTree(h, start, visited);
	}
}
