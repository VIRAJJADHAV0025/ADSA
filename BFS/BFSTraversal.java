import java.util.Scanner;

public class BFSTraversal {

    static final int MAX = 100;

    static int[] queue = new int[MAX];
    static int front = -1;
    static int rear = -1;

    // Enqueue
    static void enqueue(int vertex) {

        if (rear == MAX - 1) {
            return;
        }

        if (front == -1) {
            front = 0;
        }

        rear++;
        queue[rear] = vertex;
    }

    // Dequeue
    static int dequeue() {

        if (front == -1) {
            return -1;
        }

        int vertex = queue[front];

        if (front >= rear) {
            front = -1;
            rear = -1;
        } else {
            front++;
        }

        return vertex;
    }

    // BFS Traversal
    static void bfs(int[][] graph, int startVertex, int vertices) {

        int[] visited = new int[vertices];

        enqueue(startVertex);
        visited[startVertex] = 1;

        System.out.print("BFS Traversal: ");

        while (front != -1) {

            int currentVertex = dequeue();

            System.out.print(currentVertex + " ");

            for (int i = 0; i < vertices; i++) {

                if (graph[currentVertex][i] == 1
                        && visited[i] == 0) {

                    enqueue(i);
                    visited[i] = 1;
                }
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int vertices = sc.nextInt();

        int[][] graph = new int[vertices][vertices];

        System.out.println("Enter adjacency matrix:");

        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        System.out.print("Enter starting vertex: ");
        int startVertex = sc.nextInt();

        bfs(graph, startVertex, vertices);

        sc.close();
    }
}
