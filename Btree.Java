class BTreeNode {
    int t;  // Minimalny stopień B-drzewa (minimalna liczba dzieci)
    int[] keys;  // Tablica kluczy
    BTreeNode[] children;  // Tablica dzieci
    int n;  // Aktualna liczba kluczy
    boolean leaf;  // Czy węzeł jest liściem

    public BTreeNode(int t, boolean leaf) {
        this.t = t;
        this.leaf = leaf;
        this.keys = new int[2 * t - 1];
        this.children = new BTreeNode[2 * t];
        this.n = 0;
    }
}