public class BTreeTest {
    public static void main(String[] args) {
        BTree t = new BTree(3);
        t.insert(10);
        t.insert(20);
        t.insert(5);
        t.insert(6);
        t.insert(12);
        t.insert(30);
        t.insert(7);
        t.insert(17);

        System.out.println("Szukanie klucza 6: " + (t.search(6) != null));
        System.out.println("Szukanie klucza 15: " + (t.search(15) != null));
    }
}