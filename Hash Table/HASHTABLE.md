# Hash Function

## Description

This Java program demonstrates a simple **Hash Function**. It takes a string as input, calculates a hash value, and stores the string in a hash table.

## How It Works

1. Enter a string.
2. The hash function calculates an index.
3. The string is stored at that index in the hash table.
4. The program displays the hash value and the hash table.

## Logic

* Start with `hash = 0`.
* Read each character of the string.
* Update the hash using:

  ```text
  hash = (hash * 31 + character) % M
  ```
* `31` is a prime number that helps distribute values more evenly.
* `M` is the size of the hash table.

## Example

**Input**

```text
Enter a String: Viraj
```

**Output**

```text
Hash Value = 4

0 : null
1 : null
2 : null
3 : null
4 : Viraj
5 : null
6 : null
7 : null
8 : null
9 : null
```

## Concepts

* Hash Function
* String
* Array

