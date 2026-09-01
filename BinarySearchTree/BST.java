class Node {
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
        left = right = null;
    }
}

class BST {
    Node root;

    // Insert a node into BST Search
    Node insert(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }

        if (key < root.data) {
            root.left = insert(root.left, key);
        } else if (key > root.data) {
            root.right = insert(root.right, key);
        }

        return root;
    }

    // Search a node in BST Search
    boolean search(Node node, int key) {
        if (node == null) {
            return false;
        }

        if (node.data == key) {
            return true;
        }

        if (key < node.data) {
            return search(node.left, key);
        } else {
            return search(node.right, key);
        }
    }

    // Inorder Traversal (Left -> Root -> Right)
    void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    // Preorder Traversal (Root -> Left -> Right)
    void preorder(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    // Postorder Traversal (Left -> Right -> Root)
    void postorder(Node root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data + " ");
        }
    }

    public static void main(String[] args) {
        BST tree = new BST();

        int[] values = { 15 , 43 , 23 , 76 , 46 , 34 , 93 , 25};

        for (int value : values) {
            tree.root = tree.insert(tree.root, value);
        }

        System.out.print("Inorder Traversal: ");
        tree.inorder(tree.root);

        System.out.print("\nPreorder Traversal: ");
        tree.preorder(tree.root);

        System.out.print("\nPostorder Traversal: ");
        tree.postorder(tree.root);

        // Search key
        int key = 25;

        if (tree.search(tree.root, key)) {
            System.out.println("\n" + key + " found in BST");
        } else {
            System.out.println("\n" + key + " not found in BST");
        }
    }
}