package Set;

import Set.Circular.Set;

public class Main {
    public static void main(String[] args) {

        Set set1 = new Set();
        Set set2 = new Set();
        set1.Insert(15);
        set1.Insert(13);
        set1.Insert(10);
        set1.Insert(9);
        set2.Insert(10);
        set2.Insert(13);
        Set set = set1.Difference(set2);
    }
}
