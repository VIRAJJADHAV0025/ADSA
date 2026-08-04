# Merge Sort in Java

A simple Java implementation of the **Merge Sort** algorithm using the **Divide and Conquer** approach.

## 📌 Algorithm

Merge Sort works in three steps:

1. Divide the array into two halves.
2. Recursively sort each half.
3. Merge the two sorted halves into a single sorted array.

## 📂 File Structure

```
MergeSort.java
README.md
```

## 🚀 Features

- Uses Divide and Conquer technique.
- Recursive implementation.
- Stable sorting algorithm.
- Efficient for large datasets.
- Time Complexity: **O(n log n)**.

## 💻 Code Overview

### `main()`
- Reads the number of elements.
- Takes array input from the user.
- Calls the `divide()` function.
- Prints the sorted array.

### `divide()`
- Recursively divides the array into smaller subarrays.
- Stops when only one element remains.
- Calls `conquer()` to merge the sorted subarrays.

### `conquer()`
- Merges two sorted subarrays.
- Uses a temporary array.
- Copies the merged result back into the original array.

## ▶️ How to Run

### Compile

```bash
javac MergeSort.java
```

### Execute

```bash
java MergeSort
```

## 📝 Sample Input

```
Enter the number of elements in an Array:
6

Enter the Array elements:
38
27
43
3
9
82
```

## ✅ Output

```
Sorted Array is:
3
9
27
38
43
82
```

## ⏱️ Time Complexity

| Case | Complexity |
|------|------------|
| Best Case | O(n log n) |
| Average Case | O(n log n) |
| Worst Case | O(n log n) |

## 💾 Space Complexity

```
O(n)
```

Merge Sort requires an additional temporary array during the merge process.

## 🔁 Working

Example:

```
Original Array

[38, 27, 43, 3, 9, 82]

           Divide
              |
    -------------------
    |                 |
[38,27,43]        [3,9,82]
    |                 |
 -------           -------
 |     |           |     |
[38] [27,43]     [3] [9,82]
        |               |
      Merge           Merge
        |               |
    [27,38,43]     [3,9,82]
            \       /
             \     /
              Merge
                |
[3,9,27,38,43,82]
```

## 📖 Advantages

- Guaranteed **O(n log n)** time complexity.
- Stable sorting algorithm.
- Suitable for linked lists.
- Performs well on large datasets.

## ❌ Disadvantages

- Requires extra memory.
- Slower than Quick Sort for small arrays due to recursion overhead.
- Not an in-place sorting algorithm.

## 📚 Concepts Used

- Java
- Arrays
- Recursion
- Divide and Conquer
- Merge Sort Algorithm
