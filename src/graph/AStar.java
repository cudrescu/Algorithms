package graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class AStar {

    static class Graph {

        int nrOfVertices;
        int[][] weights;

        public Graph(int nrOfVertices, int[][] weights) {
            this.nrOfVertices = nrOfVertices;
            this.weights = weights;
        }
    }

    private static Integer f(int[] g, int[] h, int i) {
        return g[i] + h[i];
    }

    private static void aStar(Graph graph, int source, int destination) {

        int[] g = new int[graph.nrOfVertices];
        int[] h = new int[graph.nrOfVertices];
        int[] parent = new int[graph.nrOfVertices];

        PriorityQueue<Integer> open = new PriorityQueue<>(Comparator.comparing(i -> f(g, h, i)));
        Set<Integer> closed = new HashSet<>();

        int depth = 0;
        open.add(source);

        while(true) {
            if(open.isEmpty()) {
                System.out.println("No path found");
                return;
            }

            Integer node = open.poll();

            if(node == destination) {
                System.out.println(printSolution(parent, source, destination));
                return;
            }

            if(!closed.contains(node)) {
                closed.add(node);
                depth ++;
                for(int i = 0; i < graph.nrOfVertices; i++) {
                    if(graph.weights[node][i] != 0) {
                        int cost = g[node] + graph.weights[node][i];
                        if(!closed.contains(i) && !open.contains(i)) {
                            g[i] = cost;
                            h[i] = depth;
                            parent[i] = node;
                            open.add(i);
                        } else {
                            if(cost < g[i]) {
                                g[i] = cost;
                                parent[i] = node;
                                h[i] = depth;

                                if(closed.contains(i)) {
                                    closed.remove(i);
                                    open.add(i);
                                }
                            }
                        }
                    }
                }

            }
        }

    }

    private static String printSolution(int[] parent, int source, int node) {
        if(node == source) {
            return String.valueOf(node);
        }

        return printSolution(parent, source, parent[node]) + " " + String.valueOf(node);
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

        aStar(g, 0, 5);
    }
}
