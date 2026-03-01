import java.util.*;

// AVL Tree Node for balanced location storage
class AVLNode {
    String locationName;
    int height;
    AVLNode left, right;

    AVLNode(String d) {
        locationName = d;
        height = 1;
    }
}

public class LocationManagerAVL {
    private AVLNode root;

    // Helper to get height of node
    int height(AVLNode N) { return N == null ? 0 : N.height; }

    // Get Balance factor to check for imbalances
    int getBalance(AVLNode N) { return N == null ? 0 : height(N.left) - height(N.right); }

    // Right rotate utility for balancing
    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    // Left rotate utility for balancing
    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y;
    }

    public void insert(String key) { root = insert(root, key); }

    // AVL Insert with O(log n) complexity and auto-balancing
    private AVLNode insert(AVLNode node, String key) {
        if (node == null) return new AVLNode(key);
        if (key.compareTo(node.locationName) < 0) node.left = insert(node.left, key);
        else if (key.compareTo(node.locationName) > 0) node.right = insert(node.right, key);
        else return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);

        // Balancing Cases: LL, RR, LR, RL
        if (balance > 1 && key.compareTo(node.left.locationName) < 0) return rightRotate(node);
        if (balance < -1 && key.compareTo(node.right.locationName) > 0) return leftRotate(node);
        if (balance > 1 && key.compareTo(node.left.locationName) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && key.compareTo(node.right.locationName) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    // Tree Search Algorithm
    public boolean search(String key) { return search(root, key); }

    private boolean search(AVLNode node, String key) {
        if (node == null) return false;
        if (node.locationName.equals(key)) return true;
        return key.compareTo(node.locationName) < 0 ? search(node.left, key) : search(node.right, key);
    }

    // Binary Search for Module 3 Performance Analysis
    public int binarySearch(int[] arr, int x) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (arr[m] == x) return m;
            if (arr[m] < x) l = m + 1;
            else r = m - 1;
        }
        return -1;
    }
}