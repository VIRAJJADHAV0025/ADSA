import java.util.Scanner;

public class HamiltonianCycle {

    static int n;
    static int[][] graph;
    static int[] path;

    // Returns true if vertex v is already placed in path[0..pos-1]
    static boolean inPath(int v, int pos) {
        for (int i = 0; i < pos; i++) {
            if (path[i] == v) {
                return true;
            }
        }
        return false;
    }

    // Tries to fill path[pos], path[pos+1], ... ; returns true as soon as a cycle is found
    static boolean solve(int pos) {
        if (pos == n) {                                  // all vertices placed
            if (graph[path[n - 1]][path[0]] == 1) {      // last vertex connects back to the start
                printCycle();
                return true;
            }
            return false;
        }

        for (int v = 1; v < n; v++) {
            if (graph[path[pos - 1]][v] == 1 && !inPath(v, pos)) {
                path[pos] = v;                           // choose v
                if (solve(pos + 1)) {
                    return true;
                }
                path[pos] = -1;                          // backtrack: undo the choice
            }
        }
        return false;
    }

    static void printCycle() {
        StringBuilder sb = new StringBuilder("Hamiltonian cycle: ");
        for (int i = 0; i < n; i++) {
            sb.append(path[i]).append(" -> ");
        }
        sb.append(path[0]);                              // close the cycle
        System.out.println(sb);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices n: ");

        if (!sc.hasNextInt()) {
            System.out.println("Please enter a whole number.");
            return;
        }
        n = sc.nextInt();
        if (n < 1) {
            System.out.println("n must be at least 1.");
            return;
        }

        graph = new int[n][n];
        System.out.println("Enter the " + n + " x " + n + " adjacency matrix (1 = edge, 0 = no edge):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!sc.hasNextInt()) {
                    System.out.println("Matrix input is incomplete.");
                    return;
                }
                int x = sc.nextInt();
                if (x != 0 && x != 1) {
                    System.out.println("Matrix entries must be 0 or 1.");
                    return;
                }
                graph[i][j] = x;
            }
        }

        path = new int[n];
        for (int i = 0; i < n; i++) {
            path[i] = -1;
        }
        path[0] = 0;                                     // the cycle always starts at vertex 0

        if (!solve(1)) {
            System.out.println("No Hamiltonian cycle exists.");
        }
        sc.close();
    }
}