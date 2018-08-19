package graph;

import java.util.*;

public class FordFulkerson {



    static class Graph {

        int nrOfVertices;
        int[][] adjancyMatrix;

        public Graph(int nrOfVertices, int[][] adjancyMatrix) {
            this.nrOfVertices = nrOfVertices;
            this.adjancyMatrix = adjancyMatrix;
        }
    }

    private static Integer findPath(Graph g, Set<Integer> visited, int[][] f, int node, int destination, Stack<Integer> path) {

        if(node == destination) {
            path.push(node);
            return node;
        }

        for(int i = 0; i < g.nrOfVertices; i++) {
            if(g.adjancyMatrix[node][i] != 0 && !visited.contains(i)) {
                if(g.adjancyMatrix[node][i] > f[node][i]) {
                    visited.add(i);
                    Integer found = findPath(g, visited, f, i, destination, path);
                    if(found != -1) {
                        path.push(node);
                        return found;
                    }
                }
            }
        }

        return -1;
    }

    private static int fordFulkerson(Graph g, int source, int destination) {

        Stack<Integer> path = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        int[][] f = new int[g.nrOfVertices][g.nrOfVertices];
        int fMax = 0;

        visited.add(source);
        while(findPath(g, visited, f, source, destination, path) != -1) {

            Iterator<Integer> iterator = path.iterator();
            int to = iterator.next();
            int min = Integer.MAX_VALUE;

            while(iterator.hasNext()) {
                int from = iterator.next();
                if(g.adjancyMatrix[from][to] - f[from][to] < min) {
                    min = g.adjancyMatrix[from][to] - f[from][to];
                }
                to = from;
            }

            fMax += min;

            iterator = path.iterator();
            to = iterator.next();

            System.out.println();
            System.out.println("Path: ");
            while(iterator.hasNext()) {
                int from = iterator.next();
                f[from][to] += min;
                f[to][from] = -f[from][to];

                System.out.println("from: " + from + " to: " + to);

                to = from;
            }

            visited.clear();
            visited.add(source);
            path.clear();
        }

        return fMax;
    }

    public static void main(String[] args) {

        Graph g = new Graph(6, new int[][]{
                {0, 10, 10, 0, 0, 0},
                {0, 0, 2, 8, 4, 0},
                {0, 0, 0, 9, 0, 0},
                {0, 0, 0, 0, 6, 10},
                {0, 0, 0, 0, 0, 10},
                {0, 0, 0, 0, 0, 0},
        });

        System.out.println(fordFulkerson(g, 0, 5));
    }

}
