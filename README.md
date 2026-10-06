# ADSA

A collection of **Data Structures and Algorithms (DSA)** implemented in **Java**.

## 📚 Algorithms

### Merge Sort

Merge Sort is a sorting algorithm based on the **Divide and Conquer** technique. It divides the array into smaller parts, sorts them, and then merges them into a sorted array.

* **Time Complexity:** `O(n log n)`
* **Space Complexity:** `O(n)`

➡️ **Documentation:**

* [Merge Sort](MergeSort/MERGESORT.md)

---

### Hash Function

A Hash Function converts a string into an index of a hash table. This project uses a simple polynomial rolling hash with a prime multiplier (`31`) to generate the hash value.

* **Time Complexity:** `O(n)`
* **Space Complexity:** `O(M)`

➡️ **Documentation:**

* [Hash Table](HashTable/HASHTABLE.md)

---

### BST Traversal

A **Binary Search Tree (BST)** stores smaller values in the left subtree and larger values in the right subtree. This project demonstrates three common tree traversal techniques: **Pre-order, In-order, and Post-order**.

* **Pre-order:** Root → Left → Right
* **In-order:** Left → Root → Right
* **Post-order:** Left → Right → Root
* **Time Complexity:** `O(n)`
* **Space Complexity:** `O(n)` in the worst case

➡️ **Documentation:**

* [BST Traversal](BSTTraversal/BSTTraversal.md)

---

### Search Key in BST

This project demonstrates how to **search for a given key in a Binary Search Tree**. The search compares the key with the current node and moves to either the left or right subtree.

* **Best Case:** `O(1)`
* **Average Case:** `O(log n)`
* **Worst Case:** `O(n)`
* **Space Complexity:** `O(n)` in the worst case

➡️ **Documentation:**

* [BST Search](BSTSearch/BSTSearch.md)

---

### BFS Traversal

Breadth-First Search (**BFS**) is a graph traversal algorithm that visits vertices **level by level**. It uses a **queue** and a **visited array** to keep track of the vertices that have been visited.

The graph is represented using an **adjacency matrix**.

* **Data Structure:** Queue
* **Graph Representation:** Adjacency Matrix
* **Time Complexity:** `O(V²)`
* **Space Complexity:** `O(V)`

➡️ **Documentation:**

* [BFS Traversal](BFS/BFSTraversal.md)

---

### Optimal Storage on Tape

Optimal Storage on Tape is a method used to store files in the best possible order. The files are arranged from **shortest to longest** to minimize the total and average retrieval time.

* **Optimal Order:** Shortest file → Longest file
* **Time Complexity:** `O(n²)`
* **Space Complexity:** `O(n)`

➡️ **Documentation:**

* [Optimal Storage on Tape](OptimalStorageOnTape/OSOT.md)

---

### Prim's Algorithm

Prim's Algorithm finds the **Minimum Spanning Tree (MST)** of a connected, undirected, weighted graph. It is a **greedy** algorithm: it starts from one vertex and, at every step, adds the cheapest edge that connects the tree to a new vertex.

This project has two versions:

* **Matrix version:** Adjacency matrix, scans all vertices to find the cheapest one.
* **Heap version:** Adjacency list, uses a **min-heap (Priority Queue)** to find the cheapest edge.

| | Matrix Version | Heap Version |
|---|---|---|
| **Data Structure** | Arrays (`key`, `parent`, `mstSet`) | Priority Queue + `inMST` array |
| **Graph Representation** | Adjacency Matrix | Adjacency List |
| **Time Complexity** | `O(V²)` | `O(E log V)` |
| **Space Complexity** | `O(V)` extra | `O(V + E)` |

➡️ **Documentation:**

* [Prim's Algorithm](Prim'sAlgorithm/PRIMSALGORITHM.md)

---

### Longest Common Subsequence (LCS)

The **Longest Common Subsequence (LCS)** algorithm finds the longest sequence of characters that appears in two strings while maintaining their **relative order**. Characters do not need to be adjacent.

For example, for `X = ABCBDAB` and `Y = BDCABA`, one valid LCS is `BCBA` with length `4`.

This project uses **Dynamic Programming** to calculate the LCS length and then reconstructs one valid LCS by walking backwards through the DP table.

- **Data Structure:** 2D DP Array
- **Technique:** Dynamic Programming
- **Time Complexity:** `O(m × n)`
- **Space Complexity:** `O(m × n)`

| | LCS |
|---|---|
| **Approach** | Dynamic Programming |
| **DP Table** | `dp[i][j]` stores LCS length |
| **Matching Characters** | `dp[i][j] = dp[i-1][j-1] + 1` |
| **Non-Matching Characters** | `max(dp[i-1][j], dp[i][j-1])` |
| **Time Complexity** | `O(m × n)` |
| **Space Complexity** | `O(m × n)` |

➡️ **Documentation:**

* [Longest Common Subsequence](LongestCommonSubsequence/LONGESTCOMMONSUBSEQUENCE.md)


---

### Dijkstra's Algorithm
 
**Dijkstra's Algorithm** finds the **shortest distance** from a single source vertex to every other vertex in a weighted graph with **non-negative** edge weights. It is a **greedy** algorithm: at every step, it picks the unvisited vertex with the smallest distance, locks it in, and then **relaxes** the distances of its neighbours.
 
For example, in a graph with edges `0-1 (10)`, `0-3 (5)`, `1-2 (1)`, `1-3 (2)`, `2-4 (4)`, `3-4 (2)` and source `0`, the shortest distances are `0, 7, 8, 5, 7`.
 
This project uses an **adjacency matrix** and takes the graph and the source vertex as **user input**.
 
- **Data Structure:** Arrays (`distance`, `visited`)
- **Graph Representation:** Adjacency Matrix
- **Technique:** Greedy
- **Time Complexity:** `O(V²)`
- **Space Complexity:** `O(V²)` for the matrix, `O(V)` extra
| | Dijkstra |
|---|---|
| **Approach** | Greedy |
| **Distance Array** | `distance[i]` stores the best known distance from the source |
| **Relaxation** | `distance[u] + graph[u][v] < distance[v]` |
| **Unreachable Vertices** | Stay at `INF` |
| **Negative Weights** | Not supported |
 
➡️ **Documentation:**
 
* [Dijkstra's Algorithm](DijkstraAlgorithm/DIJKSTRAALGORITHM.md)
---
