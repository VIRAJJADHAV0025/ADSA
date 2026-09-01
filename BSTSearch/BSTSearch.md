# Search Key in a BST

## Description

This Java program demonstrates how to **search for a key in a Binary Search Tree (BST)**.

The program takes values from the user, creates a BST, and searches for the given key.

## How It Works

1. Enter the number of nodes.
2. Enter the values of the nodes.
3. The values are inserted into a BST.
4. Enter the key that you want to search.
5. The program compares the key with the current node.
6. If the key is smaller, it searches the left subtree.
7. If the key is larger, it searches the right subtree.
8. If the key is equal to the current node, the key is found.

## Logic

* If the current node is `null`, the key is not found.
* If `node.key == key`, return `true`.
* If `key < node.key`, search the left subtree.
* Otherwise, search the right subtree.

```text
if node is null
    return false

if node.key == key
    return true

if key < node.key
    search left subtree

else
    search right subtree
```

## Example

**Input**

```text
Enter number of nodes: 7
Enter 7 elements:
50 30 70 20 40 60 80

Enter key to search: 60
```

**BST**

```text
          50
        /    \
      30      70
     /  \    /  \
   20   40  60   80
```

**Search Process**

```text
60 > 50  -> Go right
60 < 70  -> Go left
60 == 60 -> Key found
```

**Output**

```text
Key 60 found in BST.
```

## If Key Is Not Found

**Input**

```text
Enter key to search: 90
```

**Output**

```text
Key 90 not found in BST.
```

## Concepts

* Binary Search Tree
* Node
* Searching
* Recursion
* Comparison
* Binary Tree

## Time Complexity

| Case         | Time Complexity |
| ------------ | --------------: |
| Best Case    |            O(1) |
| Average Case |        O(log n) |
| Worst Case   |            O(n) |

## Important Point

BST search is faster than searching every node because it chooses only one subtree at each step.

If:

```text
key < node.key
```

search the left subtree.

If:

```text
key > node.key
```

search the right subtree.
