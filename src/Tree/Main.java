package Tree;

import Tree.LSRS.Tree;

public class Main {
    public static void main(String[] args) {

        Tree t11 = new Tree().create('8');
        Tree t12 = new Tree().create('9');
        Tree t13 = new Tree().create('A');
        Tree t21 = new Tree().create('5', t11, t12);
        Tree t22 = new Tree().create('6', t13);
        Tree t23 = new Tree().create('7');
        Tree t31 = new Tree().create('2');
        Tree t32 = new Tree().create('3', t21, t22);
        Tree t33 = new Tree().create('4', t23);
        Tree t4 = new Tree().create('1', t31, t32, t33);


        int n = t4.leftMostChild(t4.root());
        int m = t4.rightSibling(n);
        System.out.println(m);
    }
}
