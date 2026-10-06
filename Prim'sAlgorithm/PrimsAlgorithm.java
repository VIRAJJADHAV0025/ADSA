import java.util.*;

public class PrimsAlgorithm {

    // adj.get(u) holds {neighbour, weight} pairs
    static void primMST(int V, List<List<int[]>> adj) {
        boolean[] inMST = new boolean[V];

        // Heap entry: {weight, vertex, parent}. Smallest weight comes out first.
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        List<String> edges = new ArrayList<>();
        int mstCost = 0;
        int included = 0;

        pq.add(new int[]{0, 0, -1}); // start at vertex 0 with weight 0

        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int wt = top[0];
            int u = top[1];
            int parent = top[2];

            if (inMST[u]) continue; // already in the tree, skip

            inMST[u] = true;
            included++;
            mstCost += wt;
            if (parent != -1) edges.add(parent + " - " + u + " \t" + wt);

            for (int[] nbr : adj.get(u)) {
                int v = nbr[0];
                int w = nbr[1];
                if (!inMST[v]) {
                    pq.add(new int[]{w, v, u});
                }
            }
        }

        if (included < V) {
            System.out.println("Graph is not connected. No spanning tree exists.");
            return;
        }

        System.out.println("\nEdge \tWeight");
        for (String e : edges) System.out.println(e);
        System.out.println("\nTotal weight of MST: " + mstCost);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();
        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        if (V < 1) {
            System.out.println("Number of vertices must be at least 1.");
            return;
        }

        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        System.out.println("Enter each edge as: u v weight (vertices are 0 to " + (V - 1) + ")");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();

            if (u < 0 || u >= V || v < 0 || v >= V) {
                System.out.println("Vertex out of range.");
                return;
            }
            if (w < 0) {
                System.out.println("Negative weights are not allowed.");
                return;
            }
            // undirected graph: add the edge in both directions
            adj.get(u).add(new int[]{v, w});
            adj.get(v).add(new int[]{u, w});
        }

        primMST(V, adj);
        sc.close();
    }
}
