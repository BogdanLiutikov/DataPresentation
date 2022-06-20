package PartiallyOrderedSet;

public class Main {
    public static void main(String[] args) {

        PartiallyOrderedSet set = new PartiallyOrderedSet();
        int[][] arr = new int[][]{
                {1, 2},
                {2, 4},
                {4, 6}
        };
        set.init(arr);
        set.print();
        set.sort();
        set.print();
    }
}
