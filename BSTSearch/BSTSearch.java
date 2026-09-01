import java.util.Scanner;

class Node {
    int key;
    Node left, right;

    Node(int key) {
        this.key = key;
        left = right = null;
    }
}

public class BSTSearch {

    // Insert node into BST
    static Node insert(Node node, int key) {

        if (node == null) {
            return new Node(key);
        }

        if (key < node.key) {
            node.left = insert(node.left, key);
        } 
        else if (key > node.key) {
            node.right = insert(node.right, key);
        }

        return node;
    }

    // Search key in BST
    static boolean search(Node node, int key) {

        if (node == null) {
            return false;
        }

        if (node.key == key) {
            return true;
        }

        if (key < node.key) {
            return search(node.left, key);
        }

        return search(node.right, key);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Node root = null;

        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();

        System.out.println("Enter " + n + " elements:");

        for (int i = 0; i < n; i++) {
            int key = sc.nextInt();
            root = insert(root, key);
        }

        System.out.print("Enter key to search: ");
        int key = sc.nextInt();

        if (search(root, key)) {
            System.out.println("Key " + key + " found in BST.");
        } else {
            System.out.println("Key " + key + " not found in BST.");
        }

        sc.close();
    }
}
