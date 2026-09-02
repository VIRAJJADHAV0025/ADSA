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
