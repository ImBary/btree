class BTree {
    BTreeNode root;
    int t;

    public BTree(int t) {
        this.root = null;
        this.t = t;
    }

    public BTreeNode search(int k) {
        if (root == null) {
            return null;
        } else {
            return search(root, k);
        }
    }

    private BTreeNode search(BTreeNode x, int k) {
        int i = 0;
        while (i < x.n && k > x.keys[i]) {
            i++;
        }
        if (i < x.n && k == x.keys[i]) {
            return x;
        }
        if (x.leaf) {
            return null;
        } else {
            return search(x.children[i], k);
        }
    }

    public void insert(int k) {
        if (root == null) {
            root = new BTreeNode(t, true);
            root.keys[0] = k;
            root.n = 1;
        } else {
            if (root.n == 2 * t - 1) {
                BTreeNode s = new BTreeNode(t, false);
                s.children[0] = root;
                splitChild(s, 0, root);
                root = s;
                insertNonFull(root, k);
            } else {
                insertNonFull(root, k);
            }
        }
    }

    private void insertNonFull(BTreeNode x, int k) {
        int i = x.n - 1;
        if (x.leaf) {
            while (i >= 0 && k < x.keys[i]) {
                x.keys[i + 1] = x.keys[i];
                i--;
            }
            x.keys[i + 1] = k;
            x.n = x.n + 1;
        } else {
            while (i >= 0 && k < x.keys[i]) {
                i--;
            }
            i++;
            if (x.children[i].n == 2 * t - 1) {
                splitChild(x, i, x.children[i]);
                if (k > x.keys[i]) {
                    i++;
                }
            }
            insertNonFull(x.children[i], k);
        }
    }

    private void splitChild(BTreeNode x, int i, BTreeNode y) {
        BTreeNode z = new BTreeNode(t, y.leaf);
        z.n = t - 1;
        for (int j = 0; j < t - 1; j++) {
            z.keys[j] = y.keys[j + t];
        }
        if (!y.leaf) {
            for (int j = 0; j < t; j++) {
                z.children[j] = y.children[j + t];
            }
        }
        y.n = t - 1;

        for (int j = x.n; j > i; j--) {
            x.children[j + 1] = x.children[j];
        }
        x.children[i + 1] = z;

        for (int j = x.n - 1; j >= i; j--) {
            x.keys[j + 1] = x.keys[j];
        }
        x.keys[i] = y.keys[t - 1];
        x.n = x.n + 1;
    }
}
