# Floyd-Warshall Algorithm (All-Pairs Shortest Path)

Code file: `FloydWarshall.java`

## What problem does it solve?

- You have a **weighted graph** with `n` vertices.
- You want the **shortest distance between every pair of vertices**, not just from one source.
- The distance is the smallest possible sum of edge weights along a path.

Example: in the graph below, the shortest path from `1` to `0` is `1 → 2 → 3 → 0` with total weight 5. The direct edge `1 → 0` costs 8, so it loses.

### Single-source vs all-pairs

| | Rule | Example |
|---|---|---|
| Single-source (Dijkstra) | Distances from **one** vertex to all others | Run once from vertex `0` |
| All-pairs (Floyd-Warshall) | Distances between **every** pair of vertices | One run gives the full `n × n` answer |

Floyd-Warshall also works with **negative edge weights**, as long as there is no negative cycle.

## The core idea

- Build a matrix `dist` where `dist[i][j]` = best known distance from vertex `i` to vertex `j`.
- At the start, `dist` is a copy of the graph. Missing edges are `INF`.
- Then allow the vertices to be used as **intermediate stops**, one at a time: first vertex `0`, then `0` and `1`, then `0`, `1` and `2`, and so on.
- After step `k`, `dist[i][j]` is the shortest path from `i` to `j` that only uses vertices `0..k` as stops. This is called **Dynamic Programming**.

## The rule

For every `k`, and for every pair `i`, `j`:

| Case | What to do |
|---|---|
| `dist[i][k]` or `dist[k][j]` is `INF` | Skip it. There is no route through `k` |
| `dist[i][k] + dist[k][j] < dist[i][j]` (a shorter route through `k`) | `dist[i][j] = dist[i][k] + dist[k][j]` |
| Otherwise | Leave `dist[i][j]` as it is |

- The diagonal `dist[i][i]` should be `0`. A vertex is at distance 0 from itself.
- The outer loop must be `k`. If you put `i` or `j` outside, the answer can be wrong.
- When all `n` rounds are done, `dist` holds the final answer for every pair.

### Why this works

- **Two choices:** The best path from `i` to `j` either does not use `k`, or it goes `i → k → j`.
- **Reuse:** If it goes through `k`, the two halves `i → k` and `k → j` were already solved in earlier rounds, so we just add them.
- **Take the smaller:** Keep whichever is better, the old value or the route through `k`.

## Pseudocode

```
FLOYD-WARSHALL(graph, n)

1.  Create a matrix dist[n][n]
2.  // Initialize distance matrix
3.  for i ← 0 to n-1 do
4.      for j ← 0 to n-1 do
5.          dist[i][j] ← graph[i][j]
6.      end for
7.  end for
8.  // Find all-pairs shortest paths
9.  for k ← 0 to n-1 do
10.     for i ← 0 to n-1 do
11.         for j ← 0 to n-1 do
12.             if dist[i][k] ≠ INF AND
13.                dist[k][j] ≠ INF AND
14.                dist[i][k] + dist[k][j] < dist[i][j] then
15.                 dist[i][j] ← dist[i][k] + dist[k][j]
16.             end if
17.         end for
18.     end for
19. end for
20. // Display shortest distance matrix
21. for i ← 0 to n-1 do
22.     for j ← 0 to n-1 do
23.         if dist[i][j] = INF then
24.             print "INF"
25.         else
26.             print dist[i][j]
27.         end if
28.     end for
29.     print new line
30. end for
31. END
```

## How the code maps to the pseudocode

| Pseudocode | Java |
|---|---|
| `INF` | `Integer.MAX_VALUE` |
| `dist[n][n]` (lines 1 to 7) | `int[][] dist`, copied from `graph` |
| the three `for` loops (lines 9 to 19) | `k`, `i`, `j` loops in `floydWarshall(...)` |
| lines 12 to 15 | the `if` inside the innermost loop |
| display (lines 20 to 30) | `display(...)` |

- The two `!= INF` checks stop `INF + weight` from overflowing `Integer.MAX_VALUE`.
- `readValue(...)` reads each matrix entry as a word. `INF` (any case) becomes `Integer.MAX_VALUE`, anything else is read as an integer.

## Input format

- First line: number of vertices `n`.
- Next `n` lines: the adjacency matrix, `n` entries per line.
- Type `INF` where there is **no edge**.
- Type `0` on the diagonal.
- Vertices are numbered from `0`.
- For an undirected graph, the matrix must be **symmetric**. For a directed graph, it does not have to be.

## Example

Edges (directed): `0→1 (3)`, `0→3 (7)`, `1→0 (8)`, `1→2 (2)`, `2→0 (5)`, `2→3 (1)`, `3→0 (2)`

Input:

```
4
0 3 INF 7
8 0 2 INF
5 INF 0 1
2 INF INF 0
```

### The matrix after each round

Only the cells that changed in that round are listed.

```
Start (k = none)
        0    1    2    3
   0    0    3    INF  7
   1    8    0    2    INF
   2    5    INF  0    1
   3    2    INF  INF  0

After k = 0
        0    1    2    3
   0    0    3    INF  7
   1    8    0    2    15
   2    5    8    0    1
   3    2    5    INF  0

After k = 1
        0    1    2    3
   0    0    3    5    7
   1    8    0    2    15
   2    5    8    0    1
   3    2    5    7    0

After k = 2
        0    1    2    3
   0    0    3    5    6
   1    7    0    2    3
   2    5    8    0    1
   3    2    5    7    0

After k = 3
        0    1    2    3
   0    0    3    5    6
   1    5    0    2    3
   2    3    6    0    1
   3    2    5    7    0
```

### Step by step

| Round | Cell | Check | Change |
|---|---|---|---|
| `k = 0` | `dist[1][3]` | `dist[1][0] + dist[0][3] = 8 + 7 = 15 < INF` | `INF → 15` |
| `k = 0` | `dist[2][1]` | `dist[2][0] + dist[0][1] = 5 + 3 = 8 < INF` | `INF → 8` |
| `k = 0` | `dist[3][1]` | `dist[3][0] + dist[0][1] = 2 + 3 = 5 < INF` | `INF → 5` |
| `k = 1` | `dist[0][2]` | `dist[0][1] + dist[1][2] = 3 + 2 = 5 < INF` | `INF → 5` |
| `k = 1` | `dist[3][2]` | `dist[3][1] + dist[1][2] = 5 + 2 = 7 < INF` | `INF → 7` |
| `k = 2` | `dist[0][3]` | `dist[0][2] + dist[2][3] = 5 + 1 = 6 < 7` | `7 → 6` |
| `k = 2` | `dist[1][0]` | `dist[1][2] + dist[2][0] = 2 + 5 = 7 < 8` | `8 → 7` |
| `k = 2` | `dist[1][3]` | `dist[1][2] + dist[2][3] = 2 + 1 = 3 < 15` | `15 → 3` |
| `k = 3` | `dist[1][0]` | `dist[1][3] + dist[3][0] = 3 + 2 = 5 < 7` | `7 → 5` |
| `k = 3` | `dist[2][0]` | `dist[2][3] + dist[3][0] = 1 + 2 = 3 < 5` | `5 → 3` |
| `k = 3` | `dist[2][1]` | `dist[2][3] + dist[3][1] = 1 + 5 = 6 < 8` | `8 → 6` |

- In round `k = 0`, `dist[0][2]` stays `INF`, so every route that would need `0 → 2` is skipped.
- In round `k = 1`, `dist[1][3]` is still 15. It only drops to 3 in round `k = 2`, when vertex `2` becomes available as a stop.

Output:

```
Shortest distance matrix:
0	3	5	6	
5	0	2	3	
3	6	0	1	
2	5	7	0	
```

Unreachable pairs are printed as `INF`.

## Time and space

- **Time:** `O(n³)`. Three nested loops, each running `n` times.
- **Space:** `O(n²)` for the `dist` matrix.
- It can be done in place on one matrix, because round `k` does not change row `k` or column `k` when there is no negative cycle.

## Limits of this implementation

- It returns only the **distances**, not the actual paths.
- It does **not detect negative cycles**. If a graph has one, the results are meaningless. A sign of it is a negative value on the diagonal (`dist[i][i] < 0`).
- The diagonal must be entered as `0` by the user, since the matrix is copied as it is.
- An `n × n` matrix is needed. For example, 50,000 vertices would need about 10 GB.
- Very large weights can overflow `int` when added up.
- For one source only, Dijkstra is faster (`O(n²)`), but it does not allow negative weights.

## Run it

```
javac FloydWarshall.java
java FloydWarshall
```