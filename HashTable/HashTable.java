import java.util.Scanner;

public class HashTable {

    // Hash function for String
    public static int hashFunction(String key, int M) {
        int hash = 0;
        int R = 31; // Multiplier — prime number chosen to reduce collisions

        for (int i = 0; i < key.length(); i++) {
            hash = (hash * R + key.charAt(i)) % M;
        }

        return hash;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a String: ");
        String str = sc.nextLine();

        int M = 10; // Hash table size
        String[] hashTable = new String[M];

        int index = hashFunction(str, M);

        hashTable[index] = str;

        System.out.println("Hash Value = " + index);
        System.out.println("Hash Table:");

        for (int i = 0; i < M; i++) {
            System.out.println(i + " : " + hashTable[i]);
        }

        sc.close();
    }
}
