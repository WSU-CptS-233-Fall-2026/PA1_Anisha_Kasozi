/**
 * Represents a B-Tree data structure.
 * Supports insertion, search (contains), and in-order traversal operations.
 * 
 * @author Your Name
 * @version 1.0
 */
public class BTree {
    private BTreeNode root;
    private int t;

    /**
     * Constructor for BTree.
     * 
     * @param t the minimum degree of the B-Tree
     */
    public BTree(int t) {
        this.root = null;
        this.t = t;
    }

    /**
     * Performs an in-order traversal of the B-Tree.
     * 
     * @return a String containing all the keys in sorted order
     */
    public String inorder() {
        StringBuilder sb = new StringBuilder();
        if (root != null) {
            inorderTraversalHelper(root, sb);
        }
        return sb.toString().trim();
    }

    /**
     * Helper method for recursive in-order traversal.
     * 
     * @param node the current BTreeNode being visited
     * @param sb   the StringBuilder accumulating the keys
     */
    private void inorderTraversalHelper(BTreeNode node, StringBuilder sb) {
        int i;
        for (i = 0; i < node.n; i++) {
            if (!node.leaf) {
                inorderTraversalHelper(node.children[i], sb);
            }
            sb.append(node.keys[i]).append(" ");
        }

        if (!node.leaf) {
            inorderTraversalHelper(node.children[i], sb);
        }
    }

    /**
     * Searches the tree for the presence of a specific key.
     * 
     * @param k the key to search for
     * @return true if the key is found, false otherwise
     */
    public boolean contains(int k) {
        return searchHelper(root, k) != null;
    }

    /**
     * Helper method to search for a key starting from a given node.
     * 
     * @param node the current BTreeNode
     * @param k    the key to search for
     * @return the BTreeNode containing the key, or null if not found
     */
    private BTreeNode searchHelper(BTreeNode node, int k) {
        if (node == null) {
            return null;
        }

        int i = 0;
        while (i < node.n && k > node.keys[i]) {
            i++;
        }

        if (i < node.n && k == node.keys[i]) {
            return node;
        }

        if (node.leaf) {
            return null;
        }

        return searchHelper(node.children[i], k);
    }

    /**
     * Inserts a new key into the B-Tree. Ensures duplicate keys are not inserted.
     * 
     * @param k the key to insert
     */
    public void insert(int k) {
        if (contains(k)) {
            return;
        }

        if (root == null) {
            root = new BTreeNode(t, true);
            root.keys[0] = k;
            root.n = 1;
        } else {
            if (root.n == 2 * t - 1) {
                BTreeNode s = new BTreeNode(t, false);
                s.children[0] = root;
                s.splitChild(0, root);

                int i = 0;
                if (s.keys[0] < k) {
                    i++;
                }
                s.children[i].insertNonFull(k);

                root = s;
            } else {
                root.insertNonFull(k);
            }
        }
    }
}
