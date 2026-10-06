# Dijkstra's Algorithm (Shortest Path)

Code file: `Dijkstra.java`

## What problem does it solve?

- You have a **weighted graph** and a **source** vertex.
- You want the **shortest distance** from the source to **every other vertex**.
- The distance is the smallest possible sum of edge weights along a path.

Example: in the graph below, the shortest path from `0` to `2` is `0 → 3 → 1 → 2` with total weight 8. The direct-looking route `0 → 1 → 2` costs 11, so it loses.

### Shortest path vs fewest edges

| | Rule | Example (source `0`, target `2`) |
|---|---|---|
| Fewest edges | Count the number of hops | `0 → 1 → 2` (2 edges, cost 11) |
| Shortest path | Add up the weights | `0 → 3 → 1 → 2` (3 edges, cost 8) |

Dijkstra is about **total weight**, not number of edges.

## The core idea

- Keep an array `distance[]`, where `distance[i]` = best known distance from the source to vertex `i`.
- At the start, only the source is known (`0`). Every other vertex is `INF`.
- Repeatedly pick the **unvisited vertex with the smallest distance** and lock it in as final. This is called a **Greedy** approach.
- Then try to improve the distances of its neighbours. This step is called **relaxation**.

## The rule

For each chosen vertex `u`, and for every vertex `v`:

| Case | What to do |
|---|---|
| `v` is visited, or there is no edge `u → v` (`graph[u][v] = 0`) | Skip it |
| `distance[u] + graph[u][v] < distance[v]` (a shorter route through `u`) | `distance[v] = distance[u] + graph[u][v]` |
| Otherwise | Leave `distance[v]` as it is |

- `distance[source]` starts at `0`. Everything else starts at `INF`.
- When the loop ends, `distance[]` holds the final answer for every vertex.

### Why this works

- **Greedy choice:** The unvisited vertex with the smallest distance cannot be reached any cheaper through another unvisited vertex, because every other route is already at least as long and weights are non-negative. So it is safe to lock it in.
- **Relaxation:** Going through `u` might give a neighbour a better route than the one it had. So we check and update.

## Pseudocode

```
dijkstra(G, source, n)

for i ← 0 to n-1 do
    distance[i] ← INF
    visited[i]  ← false
end for

distance[source] ← 0

for count ← 0 to n-1 do
    u ← unvisited vertex with minimum distance

    if u = -1 then
        break
    end if

    visited[u] ← true

    for v ← 0 to n-1 do
        if visited[v] = false AND
           graph[u][v] ≠ 0 AND
           distance[u] + graph[u][v] < distance[v] then
            distance[v] ← distance[u] + graph[u][v]
        end if
    end for
end for

return distance[]
```

## How the code maps to the pseudocode

| Pseudocode | Java |
|---|---|
| `INF` | `Integer.MAX_VALUE` |
| `distance[]`, `visited[]` | `int[] distance`, `boolean[] visited` |
| unvisited vertex with minimum distance | `minDistanceVertex(...)`, returns `-1` if none is reachable |
| relaxing edge `u → v` | the `if` inside the inner `for` loop |

- The Java code adds the check `distance[u] != INF` before the addition, so `INF + weight` can never overflow.
- If two vertices tie for the minimum, `minDistanceVertex` picks the one with the **lower index**, because it uses `<`.

## Input format

- First line: number of vertices `n`.
- Next `n` lines: the adjacency matrix, `n` numbers per line. `0` means no edge.
- Last line: the source vertex, from `0` to `n-1`.
- Vertices are numbered from `0`.
- For an undirected graph, the matrix must be **symmetric**. For a directed graph, it does not have to be.

## Example

Edges (undirected): `0-1 (10)`, `0-3 (5)`, `1-2 (1)`, `1-3 (2)`, `2-4 (4)`, `3-4 (2)`

Input:

```
5
0 10 0 5 0
10 0 1 2 0
0 1 0 0 4
5 2 0 0 2
0 0 4 2 0
0
```

### The step table

Each row shows `distance[]` for vertices `0, 1, 2, 3, 4` after that step. `*` marks a vertex that is now locked in.

```
Step   u    dist[0] dist[1] dist[2] dist[3] dist[4]
Init   -    0       INF     INF     INF     INF
1      0    0*      10      INF     5       INF
2      3    0*      7       INF     5*      7
3      1    0*      7*      8       5*      7
4      4    0*      7*      8       5*      7*
5      2    0*      7*      8*      5*      7*
```

### Step by step

| Step | Chosen `u` | Relaxations | Change |
|---|---|---|---|
| 1 | `0` (distance 0) | `0→1`: 0+10=10, `0→3`: 0+5=5 | `dist[1]=10`, `dist[3]=5` |
| 2 | `3` (distance 5) | `3→1`: 5+2=7 < 10, `3→4`: 5+2=7 | `dist[1]=7`, `dist[4]=7` |
| 3 | `1` (distance 7) | `1→2`: 7+1=8 | `dist[2]=8` |
| 4 | `4` (distance 7) | `4→2`: 7+4=11, not less than 8 | no change |
| 5 | `2` (distance 8) | all neighbours already visited | no change |

- In step 3, vertices `1` and `4` both have distance 7. The code picks `1` because it has the lower index.
- In step 4, the route to `2` through `4` costs 11, which is worse than the 8 already found, so it is rejected.

Output:

```
Shortest distances from vertex 0:
Vertex  Distance
0       0
1       7
2       8
3       5
4       7
```

Unreachable vertices are printed as `INF`.

## Time and space

- **Time:** `O(n²)`. Each of the `n` rounds scans all vertices to find the minimum, then scans all vertices again to relax edges.
- **Space:** `O(n²)` for the adjacency matrix. The `distance` and `visited` arrays take `O(n)`.
- With an adjacency list and a priority queue, time drops to `O((V + E) log V)`, which is better for large sparse graphs.

## Limits of this implementation

- Edge weights must be **non-negative**. Dijkstra gives wrong answers with negative weights. Use Bellman-Ford for those.
- A weight of `0` means "no edge", so real zero-weight edges cannot be represented.
- It returns only the **distances**, not the actual paths.
- A large graph needs an `n × n` matrix. For example, 50,000 vertices would need about 10 GB, so use an adjacency list instead.
- Very large weights can overflow `int` when added up.

## Run it

```
javac Dijkstra.java
java Dijkstra
```