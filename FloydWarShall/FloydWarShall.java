import java.util.Scanner;

public class FloydWarShall {

    static final int INF = Integer.MAX_VALUE;

    static int[][] floydWarshall(int[][] graph, int n) {
        // Create a matrix dist[n][n] and initialize it with the graph
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        // Find all-pairs shortest paths
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != INF
                            && dist[k][j] != INF
                            && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        return dist;
    }

    // Reads one matrix entry: the word INF (any case) or an integer
    static int readValue(Scanner sc) {
        String token = sc.next();
        if (token.equalsIgnoreCase("INF")) {
            return INF;0 
        }
        return Integer.parseInt(token);
    }

    // Display shortest distance matrix
    static void display(int[][] dist, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][j] == INF) {
                    System.out.print("INF\t");
                } else {
                    System.out.print(dist[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();

        int[][] graph = new int[n][n];
        System.out.println("Enter the adjacency matrix (" + n + " x " + n
                + "). Use INF for no edge and 0 on the diagonal:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = readValue(sc);
            }
        }

        int[][] dist = floydWarshall(graph, n);

        System.out.println("\nShortest distance matrix:");
        display(dist, n);
        sc.close();
    }
}