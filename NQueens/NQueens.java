import java.util.Scanner;

public class NQueens {

    static int N;
    static int[] board;          // board[row] = column of the queen in that row (1-indexed, 0 = empty)
    static int solutionCount = 0;
    static final int PRINT_LIMIT = 8;   // print every board only when N <= 8

    // Returns true if a queen at (row, col) is not attacked by the queens in rows 1..row-1
    static boolean isSafe(int row, int col) {
        for (int i = 1; i <= row - 1; i++) {
            if (board[i] == col) {
                return false;                        // same column
            }
            if (Math.abs(board[i] - col) == Math.abs(i - row)) {
                return false;                        // same diagonal
            }
        }
        return true;
    }

    // Tries to place a queen in the given row, then recurses to the next row
    static void nQueen(int row) {
        if (row > N) {                               // all N queens placed
            solutionCount++;
            if (N <= PRINT_LIMIT || solutionCount == 1) {
                printBoard();
            }
            return;
        }

        for (int col = 1; col <= N; col++) {
            if (isSafe(row, col)) {
                board[row] = col;                    // place queen
                nQueen(row + 1);
                board[row] = 0;                      // backtrack: remove queen
            }
        }
    }

    static void printBoard() {
        System.out.println("Solution " + solutionCount + ":");
        for (int r = 1; r <= N; r++) {
            StringBuilder sb = new StringBuilder();
            for (int c = 1; c <= N; c++) {
                sb.append(board[r] == c ? "Q " : ". ");
            }
            System.out.println(sb.toString().trim());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter N (size of the board): ");

        if (!sc.hasNextInt()) {
            System.out.println("Please enter a whole number.");
            return;
        }
        N = sc.nextInt();

        if (N < 1) {
            System.out.println("N must be at least 1.");
            return;
        }

        board = new int[N + 1];                      // index 0 unused, rows are 1..N
        nQueen(1);

        if (solutionCount == 0) {
            System.out.println("No solution exists for N = " + N + ".");
        } else {
            System.out.println("Total solutions for N = " + N + ": " + solutionCount);
            if (N > PRINT_LIMIT) {
                System.out.println("(Only the first board is shown because N > " + PRINT_LIMIT + ".)");
            }
        }
        sc.close();
    }
}