# N-Queens (Backtracking)

Code file: `NQueens.java`

## What problem does it solve?

- You have an **N × N chessboard** and **N queens**.
- You want to place all the queens so that **no two queens attack each other**.
- A queen attacks along its **row**, its **column** and both **diagonals**.

Example: for `N = 4` there are exactly 2 valid boards.

```
. Q . .        . . Q .
. . . Q        Q . . .
Q . . .        . . . Q
. . Q .        . Q . .
```

### Small boards

| N | Solutions |
|---|---|
| 1 | 1 |
| 2 | 0 |
| 3 | 0 |
| 4 | 2 |
| 6 | 4 |
| 8 | 92 |
| 10 | 724 |

For `N = 2` and `N = 3` no valid placement exists, and the program says so.

## The core idea

- Every row must hold **exactly one queen** (two queens in a row would attack each other). So we place queens **one row at a time**.
- Keep an array `board[]`, where `board[row]` = the column of the queen in that row. `0` means the row is empty.
- For the current row, try every column from `1` to `N`.
- If a column is **safe**, place the queen there and move on to the next row.
- If we reach a row where **no column is safe**, we are stuck. We **backtrack**: go back to the previous row, remove its queen, and try the next column there.
- When `row > N`, all queens are placed, so we have found a solution.

This is called **backtracking**: build the answer step by step, and undo a step as soon as it leads to a dead end.

## The rule (when is a square safe?)

A queen at `(row, col)` is checked against every queen already placed in rows `1 … row-1`. Let `i` be one of those rows.

| Case | Test | Meaning |
|---|---|---|
| Same column | `board[i] = col` | Queen in row `i` is directly above |
| Same diagonal | `abs(board[i] - col) = abs(i - row)` | Column distance equals row distance |
| Otherwise | none of the above | No attack from row `i` |

- Only rows **above** are checked, because rows below are still empty.
- Rows never clash, because we place one queen per row.

### Why the diagonal test works

- Two squares are on the same diagonal when they are the **same number of columns apart as rows apart**.
- Example: queen at row 1, column 2, and a new square at row 3, column 4. Row distance = 2, column distance = 2, so they attack.

## Pseudocode

```
NQUEEN(row)

if row > N then
    print board
    return
end if

for col ← 1 to N do
    if IS_SAFE(row, col) then
        board[row] ← col
        NQUEEN(row + 1)
        board[row] ← 0
    end if
end for


IS_SAFE(row, col)

for i ← 1 to row - 1 do
    if board[i] = col then
        return FALSE
    end if

    if |board[i] - col| = |i - row| then
        return FALSE
    end if
end for

return TRUE
```

## How the code maps to the pseudocode

| Pseudocode | Java |
|---|---|
| `NQUEEN(row)` | `nQueen(int row)` |
| `IS_SAFE(row, col)` | `isSafe(int row, int col)` |
| `board[]` | `int[] board`, size `N + 1` (index `0` unused) |
| `board[row] ← col` | `board[row] = col;` (place queen) |
| `board[row] ← 0` | `board[row] = 0;` (backtrack) |
| `print board` | `printBoard()` plus a `solutionCount++` |
| `\|x\|` | `Math.abs(x)` |

- The arrays use rows and columns `1 … N`, exactly like the pseudocode, so `board` has `N + 1` slots.
- The Java code counts every solution in `solutionCount`.
- To keep the output readable, **all** boards are printed only when `N <= 8`. For bigger `N` only the first board is printed, then the total count.

## Input format

- One line: the board size `N` (a whole number, at least `1`).
- If the input is not a number, or `N < 1`, the program prints a message and stops.

## Example

Input:

```
4
```

Output:

```
Enter N (size of the board): Solution 1:
. Q . .
. . . Q
Q . . .
. . Q .

Solution 2:
. . Q .
Q . . .
. . . Q
. Q . .

Total solutions for N = 4: 2
```

In array form, the two solutions are `board = [2, 4, 1, 3]` and `board = [3, 1, 4, 2]`.

### The search trace (N = 4)

`board` shows the queens placed so far, written as the column of each row. `✗` means `IS_SAFE` returned false.

```
Row  Try col  Board so far   Result
1    1        [1]            safe
2    1        -              ✗ same column as row 1
2    2        -              ✗ diagonal with row 1
2    3        [1,3]          safe
3    1        -              ✗ same column as row 1
3    2        -              ✗ diagonal with row 2
3    3        -              ✗ same column as row 2
3    4        -              ✗ diagonal with row 2
                             dead end → backtrack, remove row 2
2    4        [1,4]          safe
3    1        -              ✗ same column as row 1
3    2        [1,4,2]        safe
4    1        -              ✗ same column as row 1
4    2        -              ✗ same column as row 3
4    3        -              ✗ diagonal with row 3
4    4        -              ✗ same column as row 2
                             dead end → backtrack, remove row 3
3    3        -              ✗ diagonal with row 1
3    4        -              ✗ same column as row 2
                             dead end → backtrack, remove row 2
2    -        -              no columns left → backtrack, remove row 1
1    2        [2]            safe
2    4        [2,4]          safe      (cols 1, 2, 3 fail)
3    1        [2,4,1]        safe
4    3        [2,4,1,3]      safe → row 5 > N → SOLUTION 1
```

- The search then continues. Starting with column `3` in row 1 gives solution 2, `[3, 1, 4, 2]`. Starting with column `4` gives nothing.
- **Backtracking in action:** with the queen at column 1 in row 1, every path dead-ends. The search discards all of them early, instead of trying every full arrangement.

## Time and space

- **Time:** roughly `O(N!)` in the worst case. Row 1 has up to `N` choices, row 2 fewer, and so on. Each `isSafe` call costs `O(N)`. Pruning unsafe squares early makes it much faster than checking all `N^N` placements.
- **Space:** `O(N)`. The `board` array holds `N + 1` numbers, and the recursion goes at most `N` levels deep.
- The number of solutions grows fast (724 for `N = 10`), and the running time grows even faster. Values of `N` above about 14 become slow with this simple method.

## Limits of this implementation

- It finds **all** solutions, so large `N` takes a long time.
- Each `isSafe` call scans all earlier rows. Faster versions keep boolean arrays for columns and the two diagonal directions, so the check is `O(1)`.
- Only the first board is shown when `N > 8`, to avoid thousands of lines of output.
- Solutions that are rotations or mirror images of each other are counted separately. For example, the two solutions for `N = 4` are mirror images.
- Recursion depth is `N`, so extremely large `N` is limited by time long before it is limited by the stack.

## Run it

```
javac NQueens.java
java NQueens
```