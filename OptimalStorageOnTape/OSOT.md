# Optimal Storage on Tape

## Description

Optimal Storage on Tape is a method used to store files on a tape in the best possible order.

The main idea is to **store the shortest files first** and the longest files later.

This reduces the **total retrieval time** and gives the minimum **average retrieval time**.

---

## How It Works

The algorithm works in the following steps:

1. Read the number of files.
2. Read the length of each file.
3. Sort all files in **ascending order**.
4. Start calculating the retrieval time.
5. Add the length of each file to the previous retrieval time.
6. Add each retrieval time to the total.
7. Calculate the average retrieval time.
8. Display the optimal order and results.

---

## Algorithm

### Step 1: Input Files

Read the number of files and the length of each file.

Example:

```text
Number of files = 4

File lengths:
20 5 10 15
```

---

### Step 2: Sort the Files

Arrange the files from **smallest to largest**.

```text
Before sorting:
20 5 10 15

After sorting:
5 10 15 20
```

The sorted order is the **optimal order**.

---

### Step 3: Calculate Retrieval Time

The retrieval time of each file is the sum of its length and all previous file lengths.

For the files:

```text
5 10 15 20
```

The retrieval times are:

| File Length |        Retrieval Time |
| ----------: | --------------------: |
|           5 |                     5 |
|          10 |           5 + 10 = 15 |
|          15 |      5 + 10 + 15 = 30 |
|          20 | 5 + 10 + 15 + 20 = 50 |

---

### Step 4: Calculate Total Retrieval Time

Add all retrieval times:

```text
Total = 5 + 15 + 30 + 50
      = 100
```

---

### Step 5: Calculate Average Retrieval Time

The average retrieval time is:

```text
Average = Total Retrieval Time / Number of Files
```

Therefore:

```text
Average = 100 / 4
        = 25
```

---

## Pseudocode

```text
START

READ n

FOR each file
    READ file length
END FOR

SORT file lengths in ascending order

total = 0
retrieval = 0

FOR each file
    retrieval = retrieval + file length
    total = total + retrieval
END FOR

average = total / n

PRINT optimal order
PRINT total retrieval time
PRINT average retrieval time

END
```

---

## Example

### Input

```text
Number of files: 4

File lengths:
20 5 10 15
```

### Optimal Order

```text
5 10 15 20
```

### Retrieval Times

```text
5
15
30
50
```

### Results

```text
Total Retrieval Time = 100

Average Retrieval Time = 25
```

---

## Why Shortest Files Are Stored First

If a short file is placed first, it can be retrieved quickly.

Also, every file after it benefits from the shorter file being processed first.

Therefore, arranging files from **shortest to longest** gives the minimum total retrieval time.

This method is also related to the **Shortest Processing Time (SPT)** rule.

---

## Important Concepts

### Retrieval Time

Retrieval time is the time required to reach and read a particular file on the tape.

### Total Retrieval Time

It is the sum of the retrieval times of all files.

### Average Retrieval Time

It is the total retrieval time divided by the number of files.

### Optimal Order

The optimal order is the order in which files are arranged to get the minimum total retrieval time.

For this problem, the optimal order is:

**Shortest file → Longest file**

---

## Time Complexity

The algorithm uses a nested loop to sort the files.

**Time Complexity: `O(n²)`**

The calculation of retrieval time takes:

**`O(n)`**

Overall:

**`O(n²)`**

---

## Space Complexity

The algorithm uses an array to store the file lengths.

**Space Complexity: `O(n)`**

---

## Key Points

* Files are arranged in **ascending order of length**.
* The **shortest file is stored first**.
* This minimizes the total retrieval time.
* Retrieval time is calculated for every file.
* Total retrieval time is the sum of all retrieval times.
* Average retrieval time is total time divided by the number of files.
* The sorting method used in this algorithm takes `O(n²)` time.
* This algorithm follows the **Shortest Processing Time (SPT)** principle.
