package graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class PrimMST {

	static class Pair<T, U> {

		T key;
		U value;

		public Pair(T key, U value) {
			this.key = key;
			this.value = value;
		}

		public T getKey() {
			return key;
		}

		public void setKey(T key) {
			this.key = key;
		}

		public U getValue() {
			return value;
		}

		public void setValue(U value) {
			this.value = value;
		}
	}

	static class Edge {

		int from;
		int to;
		int weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Edge{" + "from=" + from + ", to=" + to + ", weight=" + weight + '}';
		}
	}

	static class Graph {

		int nrOfVertices;
		List<Integer>[] adjancyList;
		List<Edge> edges;

		Graph(int nrOfVertices) {
			this.nrOfVertices = nrOfVertices;
			this.adjancyList = new ArrayList[nrOfVertices];
			for(int i = 0; i < nrOfVertices; i++) {
				adjancyList[i] = new ArrayList<>();
			}
			this.edges = new ArrayList<>();
		}

		void addEdge(int from, int to, int weight) {
			adjancyList[from].add(to);
			adjancyList[to].add(from);
			edges.add(new Edge(from, to, weight));
		}
	}

	private static Pair<Integer, Integer> getNextNode(Queue<Pair<Integer, Integer>> verticesQueue, Set<Integer> mst) {
		Pair<Integer, Integer> currentVertex = verticesQueue.poll();
		while(mst.contains(currentVertex.getKey())) {
			currentVertex = verticesQueue.poll();
		}

		return currentVertex;
	}

	private static List<Edge> prim(Graph g, int startNode) {

		Set<Integer> mst = new LinkedHashSet<>();
		List<Edge> result = new ArrayList<>();
		Queue<Pair<Integer, Integer>> verticesQueue = new PriorityQueue<>(Comparator.comparing(Pair::getValue));

		for(int i = 0; i < g.nrOfVertices; i++) {
			if(startNode == i) {
				verticesQueue.add(new Pair<>(i, 0));
			} else {
				verticesQueue.add(new Pair<>(i, Integer.MAX_VALUE));
			}
		}

		for(int i = 0; i < g.nrOfVertices; i++) {
			Pair<Integer, Integer> nextVertex = getNextNode(verticesQueue, mst);
			if(!mst.isEmpty()) {
				result.add(new Edge(mst.toArray(new Integer[0])[mst.size()-1], nextVertex.getKey(), nextVertex.getValue()));
			}
			mst.add(nextVertex.getKey());
			for(Integer vertex : g.adjancyList[nextVertex.getKey()]) {
				if(!mst.contains(vertex)) {
					updateVerticesQueue(verticesQueue, g.edges, nextVertex.getKey(), vertex);
				}
			}
		}

		return result;
	}

	private static void updateVerticesQueue(Queue<Pair<Integer, Integer>> verticesQueue, List<Edge> edges, Integer nextVertex, Integer vertex) {
		for(Edge edge : edges) {
			if((edge.to == vertex && edge.from  == nextVertex) ||
					(edge.to == nextVertex && edge.from == vertex)) {

				List<Pair<Integer, Integer>> pairsToAdd = new ArrayList<>();
				Iterator<Pair<Integer, Integer>> iterator = verticesQueue.iterator();

				while(iterator.hasNext()) {
					Pair<Integer, Integer> pair = iterator.next();
					if(Objects.equals(pair.getKey(), vertex)) {
						iterator.remove();
						pair.setValue(Math.min(edge.weight, pair.getValue()));
						pairsToAdd.add(pair);
					}
				}

				verticesQueue.addAll(pairsToAdd);

				break;
			}
		}
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

		System.out.println(prim(g, 0));
	}

}
