# Longest Common Subsequence (LCS)

Code file: `LCS.java`

## What problem does it solve?

- You have two strings, `X` and `Y`.
- You want the **longest sequence of characters** that appears in **both**, in the **same order**.
- The characters do **not** need to be next to each other.

Example: `X = ABCBDAB`, `Y = BDCABA`
- `BCBA` is a common subsequence of length 4.
- No common subsequence of length 5 exists, so the answer is 4.

### Subsequence vs substring

| | Rule | Example from `ABCBDAB` |
|---|---|---|
| Substring | Characters must be next to each other | `BCB` |
| Subsequence | Characters can have gaps, but order is kept | `ACDB` |

LCS is about **subsequences**. Do not mix it up with "longest common substring", which is a different problem.

## The core idea

- Build a table `dp` where `dp[i][j]` = length of the LCS of the **first `i` characters of X** and the **first `j` characters of Y**.
- Solve small pieces first, then use them to solve bigger pieces. This is called **Dynamic Programming**.
- Each cell depends only on three neighbours: left, top and top-left.

## The table rule

For every `i` from 1 to `m` and `j` from 1 to `n`:

| Case | What to do |
|---|---|
| `X[i-1] == Y[j-1]` (last characters match) | `dp[i][j] = dp[i-1][j-1] + 1` |
| They do not match | `dp[i][j] = max(dp[i-1][j], dp[i][j-1])` |

- Row 0 and column 0 are all `0`. An empty string has no common part with anything.
- The answer (the length) is in the bottom-right cell: `dp[m][n]`.

### Why this works

- **Match:** The matching character is part of the LCS. So take the best result from both strings without that character, then add 1.
- **No match:** At least one of the two last characters is not used. So try dropping the last character of `X`, or of `Y`, and keep the better result.

## Getting the actual subsequence (BUILD_LCS)

The table gives only the length. To get the characters, walk backwards from `dp[m][n]`:

1. Start at `i = m`, `j = n`.
2. While `i > 0` and `j > 0`:
   - If `X[i-1] == Y[j-1]`: this character is in the LCS. Put it at the **front** of the result. Move diagonally (`i--`, `j--`).
   - Else if `dp[i-1][j] >= dp[i][j-1]`: move up (`i--`).
   - Else: move left (`j--`).
3. The result is already in the correct order, because we add each new character to the front.

## Input format

- First line: string `X`.
- Second line: string `Y`.
- Matching is **case-sensitive** (`a` and `A` are different).
- Spaces count as characters.

## Example

Input:

```
ABCBDAB
BDCABA
```

### The table

Columns are the characters of `Y`. Rows are the characters of `X`.

```
        B  D  C  A  B  A
     0  0  0  0  0  0  0
  A  0  0  0  0  1  1  1
  B  0  1  1  1  1  2  2
  C  0  1  1  2  2  2  2
  B  0  1  1  2  2  3  3
  D  0  1  2  2  2  3  3
  A  0  1  2  2  3  3  4
  B  0  1  2  2  3  4  4
```

### Walking back from `dp[7][6] = 4`

| Position (i, j) | Compare | Action | Result so far |
|---|---|---|---|
| (7, 6) | `B` vs `A`: no match | `dp[6][6]=4 >= dp[7][5]=4`, go up | |
| (6, 6) | `A` vs `A`: match | Take `A`, go diagonal | `A` |
| (5, 5) | `D` vs `B`: no match | `dp[4][5]=3 >= dp[5][4]=2`, go up | `A` |
| (4, 5) | `B` vs `B`: match | Take `B`, go diagonal | `BA` |
| (3, 4) | `C` vs `A`: no match | `dp[2][4]=1 < dp[3][3]=2`, go left | `BA` |
| (3, 3) | `C` vs `C`: match | Take `C`, go diagonal | `CBA` |
| (2, 2) | `B` vs `D`: no match | `dp[1][2]=0 < dp[2][1]=1`, go left | `CBA` |
| (2, 1) | `B` vs `B`: match | Take `B`, go diagonal | `BCBA` |
| (1, 0) | `j = 0`, stop | | `BCBA` |

Output:

```
Length of LCS: 4
LCS: BCBA
```

## The answer may not be unique

- `BCBA` is one valid LCS. `BDAB` is another one for the same input.
- The code picks one based on the tie-break rule (`>=` means go up when equal).
- All valid answers have the same length.

## Time and space

- **Time:** `O(m * n)`. Every cell is filled once. Rebuilding the string takes `O(m + n)`.
- **Space:** `O(m * n)` for the table.
- If you only need the **length**, two rows are enough, so space drops to `O(n)`. But then you cannot rebuild the subsequence, because the walk-back needs the full table.

## Limits of this implementation

- It returns **one** LCS, not all of them.
- Very long strings (for example 50,000 characters each) will need about 10 GB for the table. Use the two-row version if you only need the length.
- It compares single `char` values, so rare characters made of two Java chars (some emojis) are treated as two separate characters.

## Run it

```
javac LCS.java
java LCS
```