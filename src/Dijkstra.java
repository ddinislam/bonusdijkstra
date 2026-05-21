import java.util.*;

public class Dijkstra {

    // ─── Edge ────────────────────────────────────────────────────────────────

    static class Edge {
        int destination;
        int weight;

        Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    // ─── Graph ───────────────────────────────────────────────────────────────

    static class Graph {
        private final int vertices;
        private final List<List<Edge>> adjacencyList;

        Graph(int vertices) {
            this.vertices = vertices;
            adjacencyList = new ArrayList<>();
            for (int i = 0; i < vertices; i++) {
                adjacencyList.add(new ArrayList<>());
            }
        }

        /** Adds a directed weighted edge from src to dest. */
        void addEdge(int src, int dest, int weight) {
            adjacencyList.get(src).add(new Edge(dest, weight));
            adjacencyList.get(dest).add(new Edge(src, weight)); // undirected
        }

        /**
         * Dijkstra's Algorithm (O(V²) — simple array-based, no priority queue).
         * Prints the shortest distance from 'start' to every other vertex.
         */
        void dijkstra(int start) {
            int[] dist    = new int[vertices];
            boolean[] visited = new boolean[vertices];

            // Initialize all distances to infinity
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[start] = 0;

            System.out.println("=".repeat(45));
            System.out.printf("  Dijkstra's Algorithm  |  Start vertex: %d%n", start);
            System.out.println("=".repeat(45));

            for (int step = 0; step < vertices; step++) {
                // Pick the unvisited vertex with the smallest distance
                int u = minDistance(dist, visited);
                if (u == -1) break; // remaining vertices are unreachable

                visited[u] = true;

                // Relax edges from u
                for (Edge edge : adjacencyList.get(u)) {
                    int v = edge.destination;
                    int w = edge.weight;

                    if (!visited[v] && dist[u] != Integer.MAX_VALUE
                            && dist[u] + w < dist[v]) {
                        dist[v] = dist[u] + w;
                    }
                }
            }

            printResult(start, dist);
        }

        /** Returns the index of the unvisited vertex with the minimum distance. */
        private int minDistance(int[] dist, boolean[] visited) {
            int min = Integer.MAX_VALUE;
            int minIndex = -1;

            for (int v = 0; v < vertices; v++) {
                if (!visited[v] && dist[v] <= min) {
                    min = dist[v];
                    minIndex = v;
                }
            }
            return minIndex;
        }

        /** Prints a formatted distance table. */
        private void printResult(int start, int[] dist) {
            System.out.printf("%-10s %-15s%n", "Vertex", "Distance from " + start);
            System.out.println("-".repeat(28));
            for (int i = 0; i < vertices; i++) {
                String distance = (dist[i] == Integer.MAX_VALUE) ? "∞ (unreachable)" : String.valueOf(dist[i]);
                System.out.printf("%-10d %-15s%n", i, distance);
            }
            System.out.println("=".repeat(45));
        }
    }

    // ─── Main ────────────────────────────────────────────────────────────────

    public static void main(String[] args) {
        /*
         * Example graph (6 vertices):
         *
         *       (1)
         *    0 ─────── 1
         *    |  \      |
         * (4)|   \(2)  |(2)
         *    |    \    |
         *    2     3 ──4
         *    |  (5) \  |(3)
         * (1)|       \ |
         *    5────────(1)
         *         (6)
         */
        Graph g = new Graph(6);

        g.addEdge(0, 1, 1);
        g.addEdge(0, 2, 4);
        g.addEdge(0, 3, 2);
        g.addEdge(1, 3, 1);
        g.addEdge(1, 4, 2);
        g.addEdge(2, 5, 1);
        g.addEdge(3, 4, 3);
        g.addEdge(3, 5, 5);
        g.addEdge(4, 5, 1);

        g.dijkstra(0);  // shortest paths from vertex 0
        g.dijkstra(2);  // shortest paths from vertex 2
    }
}
