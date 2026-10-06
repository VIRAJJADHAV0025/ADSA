import java.util.Scanner;

public class LCS {

    // dp[i][j] = length of LCS of X[0..i-1] and Y[0..j-1]
    static int[][] lcsLength(String X, String Y) {
        int m = X.length();
        int n = Y.length();
        int[][] dp = new int[m + 1][n + 1]; // row 0 and column 0 stay 0

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp;
    }

    // Walk back from dp[m][n] to rebuild one LCS
    static String buildLCS(String X, String Y, int[][] dp) {
        int i = X.length();
        int j = Y.length();
        StringBuilder result = new StringBuilder();

        while (i > 0 && j > 0) {
            if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                result.insert(0, X.charAt(i - 1)); // prepend
                i--;
                j--;
            } else if (dp[i - 1][j] >= dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        return result.toString();
    }

    static void printTable(String X, String Y, int[][] dp) {
        System.out.print("\n      ");
        for (int j = 0; j < Y.length(); j++) System.out.printf("%3c", Y.charAt(j));
        System.out.println();

        for (int i = 0; i <= X.length(); i++) {
            System.out.print(i == 0 ? "  " : " " + X.charAt(i - 1));
            for (int j = 0; j <= Y.length(); j++) System.out.printf("%3d", dp[i][j]);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first string (X): ");
        String X = sc.nextLine();
        System.out.print("Enter second string (Y): ");
        String Y = sc.nextLine();

        int[][] dp = lcsLength(X, Y);
        String lcs = buildLCS(X, Y, dp);

        printTable(X, Y, dp);
        System.out.println("\nLength of LCS: " + dp[X.length()][Y.length()]);
        System.out.println("LCS: " + (lcs.isEmpty() ? "(empty)" : lcs));

        sc.close();
    }
}