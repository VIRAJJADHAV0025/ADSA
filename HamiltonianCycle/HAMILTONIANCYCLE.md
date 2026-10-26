# Hamiltonian Cycle (Backtracking)

Code file: `HamiltonianCycle.java`

## What problem does it solve?

- You have a **graph** with `n` vertices.
- You want a **cycle** that visits **every vertex exactly once** and then returns to the starting vertex.
- Such a cycle is called a **Hamiltonian cycle**. If none exists, the graph has no Hamiltonian cycle.

Example: in the graph below, `0 → 1 → 2 → 4 → 3 → 0` visits all 5 vertices once and comes back to `0`.

```
Edges: 0-1, 0-3, 1-2, 1-3, 1-4, 2-4, 3-4
```

### Hamiltonian cycle vs a normal path

| | Rule | Example |
|---|---|---|
| Any path | Walk along edges, vertices may repeat | `0 → 1 → 3 → 1 → 2` |
| Hamiltonian cycle | Every vertex exactly once, last vertex connects back to the first | `0 → 1 → 2 → 4 → 3 → 0` |

A Hamiltonian cycle is about **visiting every vertex once**, not about using every edge.

## The core idea

- Keep an array `path[]`, where `path[i]` = the vertex placed at position `i`.
- A cycle can start anywhere, so we **always start at vertex `0`**: `path[0] = 0`. All other positions start as `-1` (empty).
- Fill the positions one by one. For position `pos`, try every vertex `v` from `1` to `n-1`.
- A vertex `v` is allowed only if:
  - there is an **edge** from the previous vertex `path[pos-1]` to `v`, and
  - `v` is **not already in the path**.
- If we fill all `n` positions, we check one last thing: is there an edge from the last vertex **back to `path[0]`**? If yes, we have a cycle.
- If a position has no valid vertex, we **backtrack**: go back one position, clear it (`-1`) and try the next vertex there.

## The rule

For position `pos` and a candidate vertex `v`:

| Case | What to do |
|---|---|
| `graph[path[pos-1]][v] = 0` (no edge from the previous vertex) | Skip `v` |
| `v` is already in `path` | Skip `v` |
| Otherwise | Set `path[pos] = v` and try the next position |

When `pos = n`:

| Case | What to do |
|---|---|
| `graph[path[n-1]][path[0]] = 1` | Print the path and return `TRUE` |
| Otherwise | Return `FALSE` (backtrack) |

### Why this works

- **Fixed start:** A cycle visits every vertex, so it passes through vertex `0`. Rotating a cycle gives the same cycle, so starting at `0` loses nothing.
- **No repeats:** Checking `v` against the path guarantees each vertex is used at most once. With `n` positions filled, every vertex is used exactly once.
- **Closing edge:** A path through all vertices is only a cycle if the last vertex connects back to the first, so that check is done at the end.
- **Backtracking:** If a choice leads to a dead end, undoing it and trying another vertex covers every possible path, so no cycle is missed.

## Pseudocode

```
Hamiltonian(n, graph)

path[0] ← 0
for i ← 1 to n-1 do
    path[i] ← -1
end for

Solve(1)


Solve(pos)

if pos = n then
    if graph[path[n-1]][path[0]] = 1 then
        print path
        return TRUE
    end if
    return FALSE
end if

for v ← 1 to n-1 do
    if graph[path[pos-1]][v] = 1 AND v is not in path then
        path[pos] ← v

        if Solve(pos + 1) = TRUE then
            return TRUE
        end if

        path[pos] ← -1
    end if
end for

return FALSE
```

## How the code maps to the pseudocode

| Pseudocode | Java |
|---|---|
| `Hamiltonian(n, graph)` | the setup in `main` |
| `Solve(pos)` | `solve(int pos)` |
| `path[]` | `int[] path` |
| `graph[][]` | `int[][] graph` (adjacency matrix of `0` and `1`) |
| `v is not in path` | `!inPath(v, pos)` |
| `path[pos] ← -1` | `path[pos] = -1;` (backtrack) |
| `print path` | `printCycle()` |

- `inPath(v, pos)` only looks at `path[0 … pos-1]`, the positions filled so far.
- `printCycle()` prints `path[0]` again at the end to show the cycle closing.
- The loop starts at `v = 1`, because vertex `0` is always at `path[0]` already.
- The program stops at the **first** cycle found (`solve` returns `true` all the way back up).

## Input format

- First line: number of vertices `n`.
- Next `n` lines: the adjacency matrix, `n` numbers per line. `1` means an edge, `0` means no edge.
- Vertices are numbered from `0`.
- For an undirected graph, the matrix must be **symmetric**. For a directed graph, it does not have to be.
- Every entry must be `0` or `1`. Anything else is rejected.

## Example 1: a cycle exists

Edges (undirected): `0-1`, `0-3`, `1-2`, `1-3`, `1-4`, `2-4`, `3-4`

Input:

```
5
0 1 0 1 0
1 0 1 1 1
0 1 0 0 1
1 1 0 0 1
0 1 1 1 0
```

Each step shows `path[]` after placing a vertex.

| Step | `pos` | Previous vertex | Chosen `v` | `path` |
|---|---|---|---|---|
| 1 | 1 | `0` | `1` (edge 0-1) | `[0, 1, -1, -1, -1]` |
| 2 | 2 | `1` | `2` (edge 1-2) | `[0, 1, 2, -1, -1]` |
| 3 | 3 | `2` | `4` (edge 2-4, `3` has no edge from `2`) | `[0, 1, 2, 4, -1]` |
| 4 | 4 | `4` | `3` (edge 4-3) | `[0, 1, 2, 4, 3]` |
| 5 | 5 = n | last = `3` | check `graph[3][0] = 1` | cycle found |

Output:

```
Hamiltonian cycle: 0 -> 1 -> 2 -> 4 -> 3 -> 0
```

- Here every first choice worked, so no backtracking was needed.

## Example 2: no cycle exists

Edges (undirected): `0-1`, `0-3`, `1-2`, `1-3`, `1-4`, `2-4`

Input:

```
5
0 1 0 1 0
1 0 1 1 1
0 1 0 0 1
1 1 0 0 0
0 1 1 0 0
```

### The search trace

`✗` marks a dead end. Cleared positions go back to `-1`.

```
Path so far      Next step                              Result
[0]              try 1 (edge 0-1)                       [0,1]
[0,1]            try 2 (edge 1-2)                       [0,1,2]
[0,1,2]          try 4 (edge 2-4)                       [0,1,2,4]
[0,1,2,4]        4's neighbours 1 and 2 already used    ✗ backtrack
[0,1,2]          no more choices                        ✗ backtrack
[0,1]            try 3 (edge 1-3)                       [0,1,3]
[0,1,3]          3's neighbours 0 and 1 already used    ✗ backtrack
[0,1]            try 4 (edge 1-4)                       [0,1,4]
[0,1,4]          try 2 (edge 4-2)                       [0,1,4,2]
[0,1,4,2]        2's neighbours 1 and 4 already used    ✗ backtrack
[0,1,4]          no more choices                        ✗ backtrack
[0,1]            no more choices                        ✗ backtrack
[0]              try 3 (edge 0-3)                       [0,3]
[0,3]            try 1 (edge 3-1)                       [0,3,1]
[0,3,1]          try 2 (edge 1-2)                       [0,3,1,2]
[0,3,1,2]        try 4 (edge 2-4)                       [0,3,1,2,4]
[0,3,1,2,4]      pos = n, graph[4][0] = 0               ✗ no edge back to 0
[0,3,1]          try 4 (edge 1-4)                       [0,3,1,4]
[0,3,1,4]        try 2 (edge 4-2)                       [0,3,1,4,2]
[0,3,1,4,2]      pos = n, graph[2][0] = 0               ✗ no edge back to 0
...              everything exhausted                   ✗ return FALSE
```

Output:

```
No Hamiltonian cycle exists.
```

- Two paths reach all 5 vertices, but neither ends next to `0`, so both fail the closing-edge check.
- The other failures are dead ends caught earlier, where a vertex has no unused neighbour left.

## Time and space

- **Time:** `O(n!)` in the worst case. Position 1 has up to `n-1` choices, position 2 up to `n-2`, and so on. Pruning dead ends early makes real running time much smaller.
- **Space:** `O(n²)` for the adjacency matrix. The `path` array and the recursion take `O(n)`.
- The Hamiltonian cycle problem is **NP-complete**, so no known algorithm solves it quickly for all large graphs.

## Limits of this implementation

- It finds only the **first** cycle, not all of them.
- It is practical only for small graphs. Beyond about 20 to 25 vertices it can become very slow.
- `inPath` scans the path each time, costing `O(n)` per check. A `visited[]` array would make that `O(1)`.
- The graph is stored as an `n × n` matrix, so a large sparse graph wastes memory. An adjacency list would be better.
- A single vertex (`n = 1`) has a cycle only if it has a self-loop (`graph[0][0] = 1`), because the closing check uses `graph[0][0]`.
- For `n = 2`, a cycle `0 → 1 → 0` is reported if the edge `0-1` exists, which is only a back-and-forth walk. Treat results for very small `n` with care.

## Run it

```
javac HamiltonianCycle.java
java HamiltonianCycle
```