# BST Traversal

## Description

This Java program demonstrates a **Binary Search Tree (BST)** and performs three types of tree traversal:

* Pre-order traversal
* In-order traversal
* Post-order traversal

The program takes values from the user and creates a BST.

## How It Works

1. Enter the number of nodes.
2. Enter the values of the nodes.
3. The first value becomes the root.
4. Smaller values are inserted into the left subtree.
5. Larger values are inserted into the right subtree.
6. The program performs Pre-order, In-order, and Post-order traversal.
7. The traversal results are displayed.

## Logic

### Insert Node

* If the tree is empty, create a new node.
* If the value is smaller than the current node, insert it into the left subtree.
* If the value is larger than the current node, insert it into the right subtree.

```text
if root is null
    create new node

if data < root.data
    insert into left subtree

if data > root.data
    insert into right subtree
```

### Pre-order

Pre-order follows:

```text
Root -> Left -> Right
```

### In-order

In-order follows:

```text
Left -> Root -> Right
```

For a BST, In-order traversal gives the elements in **sorted order**.

### Post-order

Post-order follows:

```text
Left -> Right -> Root
```

## Example

**Input**

```text
Enter number of nodes: 7
Enter 7 values:
50 30 70 20 40 60 80
```

**BST**

```text
          50
        /    \
      30      70
     /  \    /  \
   20   40  60   80
```

**Output**

```text
Preorder: 50 30 20 40 70 60 80
Inorder: 20 30 40 50 60 70 80
Postorder: 20 40 30 60 80 70 50
```

## Concepts

* Binary Search Tree
* Node
* Recursion
* Pre-order Traversal
* In-order Traversal
* Post-order Traversal
* Binary Tree

## Time Complexity

| Operation  | Average Case | Worst Case |
| ---------- | -----------: | ---------: |
| Insertion  |     O(log n) |       O(n) |
| Pre-order  |         O(n) |       O(n) |
| In-order   |         O(n) |       O(n) |
| Post-order |         O(n) |       O(n) |

## Important Point

A normal BST does not always have O(log n) complexity. If values are inserted in sorted order, the tree can become skewed and operations can take O(n).
