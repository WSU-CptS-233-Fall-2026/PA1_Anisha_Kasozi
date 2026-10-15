public class PA1Main {
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

        System.out.println("In-order Traversal:");
        System.out.println(t.inorder());

        System.out.println("Contains 6? " + t.contains(6));
        System.out.println("Contains 15? " + t.contains(15));
        System.out.println("Contains 30? " + t.contains(30));
    }
}
