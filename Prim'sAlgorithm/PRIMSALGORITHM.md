# Prim's Algorithm (Heap / Priority Queue) - Minimum Spanning Tree

Code file: `PrimsAlgorithm.java`

## What problem does it solve?

- You have a graph with weighted edges (for example, cities connected by roads with distances).
- You want to connect **all** vertices using the **least total weight**.
- The result must have **no loops**.
- That result is called the **Minimum Spanning Tree (MST)**.
- An MST always has exactly `V - 1` edges (V = number of vertices).
- The graph must be **connected**, **undirected** and **weighted**.

## The core idea

- Start with one vertex. This is your "tree".
- Look at all edges that connect the tree to a vertex outside the tree.
- Pick the **cheapest** one and add that vertex to the tree.
- Repeat until all vertices are in the tree.

This is called a **greedy** approach: at every step, take the cheapest option available right now. For MSTs, this always gives the best final answer.

## The two data structures

| Structure | What it does |
|---|---|
| `inMST[v]` | `true` if vertex `v` is already in the tree. Stops us adding a vertex twice. |
| Min-heap (`PriorityQueue`) | Holds `(edge weight, vertex, parent)`. The smallest weight always comes out first. |

The graph is stored as an **adjacency list**: `adj.get(u)` is a list of `{neighbour, weight}` pairs.

## Steps

1. Push `(0, 0, -1)` into the heap. This means: reach vertex 0 with cost 0, no parent.
2. While the heap is not empty:
   - Pop the cheapest entry `(wt, u, parent)`.
   - If `u` is already in the tree, **skip it**.
   - Otherwise:
     - Mark `u` as in the tree.
     - Add `wt` to the total cost.
     - Save the edge `parent - u`.
     - Push every neighbour `v` of `u` into the heap as `(weight of u-v, v, u)`. Skip neighbours already in the tree.
3. If fewer than `V` vertices got included, the graph is not connected.
4. Print the edges and the total cost.

## Why skipping is safe

- The same vertex can sit in the heap more than once, with different weights.
- The **cheapest** copy always comes out first. That copy gets the vertex into the tree.
- Later copies are more expensive and are skipped by the `inMST` check.
- Not pushing neighbours that are already in the tree is only a small saving. The answer stays the same.

## Input format

- Number of vertices.
- Number of edges.
- Then one line per edge: `u v weight`.
- Vertices are numbered from `0`.
- Each edge is stored in both directions, because the graph is undirected.

## Example

Graph edges: `0-1 (10)`, `0-2 (15)`, `0-3 (30)`, `1-3 (40)`, `2-3 (50)`

Input:

```
4
5
0 1 10
0 2 15
0 3 30
1 3 40
2 3 50
```

### Dry run

| Step | Pop (weight, vertex) | Action | Total cost | Heap after |
|---|---|---|---|---|
| 1 | (0, 0) | Include 0. Push neighbours 1, 2, 3 | 0 | (10,1) (15,2) (30,3) |
| 2 | (10, 1) | Include 1. Push neighbour 3 (cost 40). 0 is already in. | 10 | (15,2) (30,3) (40,3) |
| 3 | (15, 2) | Include 2. Push neighbour 3 (cost 50). | 25 | (30,3) (40,3) (50,3) |
| 4 | (30, 3) | Include 3. All neighbours already in. | 55 | (40,3) (50,3) |
| 5 | (40, 3) | 3 is already in the tree. Skip. | 55 | (50,3) |
| 6 | (50, 3) | 3 is already in the tree. Skip. | 55 | empty |

Output:

```
Edge    Weight
0 - 1   10
0 - 2   15
0 - 3   30

Total weight of MST: 55
```

## Time and space

- **Time:** `O(E log V)`. Each edge is pushed at most twice (once per direction). Each push or pop costs `O(log E)`, which is `O(log V)`.
- **Space:** `O(V + E)` for the adjacency list, the heap and the `inMST` array.
- Best for sparse graphs (few edges compared to `V^2`).

## Limits of this implementation

- **Undirected graphs only.**
- **No negative weights.** The input check blocks them.
- **Disconnected graphs** have no spanning tree. The code detects this and prints a message instead of a wrong total.
- **Duplicate edges** between the same two vertices are allowed. The cheaper one wins automatically.

## Run it

```
javac PrimsPQ.java
java PrimsPQ
```