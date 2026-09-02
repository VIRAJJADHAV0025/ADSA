# BFS Traversal

## Description

This Java program demonstrates **Breadth-First Search (BFS)** traversal of a graph.

BFS is a graph traversal algorithm that visits vertices **level by level**. It uses a **queue** to store vertices that need to be visited and a **visited array** to avoid visiting the same vertex multiple times.

The graph is represented using an **adjacency matrix**.

## How It Works

1. Enter the number of vertices.
2. Enter the adjacency matrix.
3. Enter the starting vertex.
4. Add the starting vertex to the queue.
5. Mark the starting vertex as visited.
6. Remove a vertex from the queue.
7. Print the current vertex.
8. Check all vertices connected to the current vertex.
9. If a vertex is connected and not visited, add it to the queue.
10. Mark the new vertex as visited.
11. Continue until the queue becomes empty.

## Logic

### Queue

BFS uses a **queue**, which follows:

```text
FIFO
First In, First Out
````

The queue has two pointers:

* `front` points to the element to be removed.
* `rear` points to the position where the new element is inserted.

### Enqueue

Enqueue adds a vertex to the queue.

```text
IF rear == MAX - 1
    RETURN

IF front == -1
    front = 0

rear = rear + 1
queue[rear] = vertex
```

### Dequeue

Dequeue removes a vertex from the front of the queue.

```text
IF front == -1
    RETURN -1

vertex = queue[front]

IF front >= rear
    front = -1
    rear = -1
ELSE
    front = front + 1

RETURN vertex
```

### BFS Algorithm

```text
Set all visited values to 0

Enqueue(startVertex)
visited[startVertex] = 1

WHILE queue is not empty

    currentVertex = Dequeue()

    PRINT currentVertex

    FOR i = 0 TO vertices - 1

        IF graph[currentVertex][i] == 1
           AND visited[i] == 0

            Enqueue(i)
            visited[i] = 1
```

## Example

Consider the following graph:

```text
0 ---- 1
|      |
|      |
2 ---- 3 ---- 4
```

The corresponding adjacency matrix is:

```text
0 1 1 0 0
1 0 0 1 0
1 0 0 1 0
0 1 1 0 1
0 0 0 1 0
```

### Input

```text
Enter number of vertices: 5

Enter adjacency matrix:
0 1 1 0 0
1 0 0 1 0
1 0 0 1 0
0 1 1 0 1
0 0 0 1 0

Enter starting vertex: 0
```

### Output

```text
BFS Traversal: 0 1 2 3 4
```

## Step-by-Step Execution

Starting vertex is `0`.

```text
Queue: [0]
Visited: 0
```

Remove `0` from the queue.

Its adjacent vertices are `1` and `2`.

```text
Queue: [1, 2]
BFS: 0
```

Remove `1`.

Vertex `3` is connected to `1` and is not visited.

```text
Queue: [2, 3]
BFS: 0 1
```

Remove `2`.

Its connected vertices are already visited.

```text
Queue: [3]
BFS: 0 1 2
```

Remove `3`.

Vertex `4` is not visited, so add it.

```text
Queue: [4]
BFS: 0 1 2 3
```

Remove `4`.

```text
Queue: []
BFS: 0 1 2 3 4
```

The queue is empty, so BFS stops.

## Concepts

* Breadth-First Search
* Graph
* Adjacency Matrix
* Queue
* FIFO
* Visited Array
* Graph Traversal

## Time Complexity

For an adjacency matrix, BFS takes:

```text
O(V²)
```

where `V` is the number of vertices.

For every visited vertex, the program checks all `V` possible adjacent vertices.

## Space Complexity

The queue and visited array require:

```text
O(V)
```

space.

The adjacency matrix itself requires:

```text
O(V²)
```

space.

## Important Points

* BFS uses a **Queue**.
* Queue follows **FIFO**.
* BFS visits vertices **level by level**.
* The `visited` array prevents repeated visits.
* `graph[i][j] == 1` means there is an edge between vertex `i` and vertex `j`.
* This program uses an **adjacency matrix**.
* Vertex numbering starts from `0`.
* BFS does not require recursion.

