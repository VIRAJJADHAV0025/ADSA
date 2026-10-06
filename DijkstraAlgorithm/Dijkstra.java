import java.util.Scanner;

public class Dijkstra {

    static final int INF = Integer.MAX_VALUE;

    static int findMinVertex(int[] distance, boolean[] visited, int n) {
        int minDistance = INF;
        int minVertex = -1;

        for (int i = 0; i < n; i++) {
            if (!visited[i] && distance[i] < minDistance) {
                minDistance = distance[i];
                minVertex = i;
            }
        }

        return minVertex;
    }

    static int[] dijkstra(int[][] graph, int source, int n) {

        int[] distance = new int[n];
        boolean[] visited = new boolean[n];

        // Initialize distances
        for (int i = 0; i < n; i++) {
            distance[i] = INF;
            visited[i] = false;
        }

        distance[source] = 0;

        // Main Dijkstra loop
        for (int count = 0; count < n - 1; count++) {

            int u = findMinVertex(distance, visited, n);

            if (u == -1) {
                break;
            }

            visited[u] = true;

            // Relax adjacent vertices
            for (int v = 0; v < n; v++) {

                if (!visited[v]
                        && graph[u][v] != 0
                        && distance[u] != INF
                        && distance[u] + graph[u][v] < distance[v]) {

                    distance[v] = distance[u] + graph[u][v];
                }
            }
        }

        return distance;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();

        int[][] graph = new int[n][n];

        System.out.println("Enter adjacency matrix:");
        System.out.println("(Enter 0 if there is no edge)");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();

                if (graph[i][j] < 0) {
                    System.out.println(
                            "Dijkstra's Algorithm does not support negative edge weights."
                    );
                    sc.close();
                    return;
                }
            }
        }

        System.out.print("Enter source vertex (0 to " + (n - 1) + "): ");
        int source = sc.nextInt();

        if (source < 0 || source >= n) {
            System.out.println("Invalid source vertex.");
            sc.close();
            return;
        }

        int[] distance = dijkstra(graph, source, n);

        System.out.println("\nShortest distances from vertex " + source + ":");

        for (int i = 0; i < n; i++) {

            if (distance[i] == INF) {
                System.out.println(
                        "Vertex " + i + " : INF (unreachable)"
                );
            } else {
                System.out.println(
                        "Vertex " + i + " : " + distance[i]
                );
            }
        }

        sc.close();
    }
}