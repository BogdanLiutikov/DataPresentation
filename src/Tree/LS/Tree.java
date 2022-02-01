package Tree.LS;

import java.util.NoSuchElementException;

public class Tree implements ATD_Tree {

    static class Node {
        private int next; //для SPACE
        private char label;//значение
        private Son son;//ссылка на дочерний элемент

        Node(int next, char label, Son son) {
            this.next = next;
            this.label = label;
            this.son = son;
        }

        Node(int next) {
            this.next = next;
        }

        Node(char label) {
            this.label = label;
        }
    }

    static class Son {
        private int index;//индекс самого себя в массиве
        private Son next;//следующий дочерний элемент

        Son(int index, Son next) {
            this.index = index;
            this.next = next;
        }

        Son(int index) {
            this.index = index;
        }
    }

    private int root;
    private static final int SIZE = 10;
    public static Node[] mem;
    private static int SPACE;

    static {
        mem = new Node[SIZE];
        for (int i = 0; i < SIZE - 1; i++) {
            mem[i] = new Node(i + 1);
        }
        mem[SIZE - 1] = new Node(-1);
        SPACE = 0;
    }

    public Tree() {
        root = -1;
    }

    public Tree(int root) {
        this.root = root;
    }

    @Override
    public int parent(int n) {
        if (n == root)
            return -1;
        return findParent(n, root);
    }

    //прямой проход
    private int findParent(int n, int root) {

        Node cur = mem[root];

        if (cur.son == null)
            return -1;

        int leftMostChild = cur.son.index;
        if (leftMostChild == n)
            return root;


        int p = findParent(n, cur.son.index);
        if (p != -1)
            return p;


        Son sibling = cur.son.next;
        while (sibling != null) {
            if (sibling.index == n)
                return root;
            p = findParent(n, sibling.index);
            if (p != -1)
                return p;
            sibling = sibling.next;
        }
        return -1;
    }

    @Override
    public int leftMostChild(int n) {
        if (n == root)
            return mem[root].son.index;

        int parent = findParent(n, root);
        if (parent == -1) return -1;

        return mem[n].son.index;
    }

    @Override
    public int rightSibling(int n) {
        if (n == root)
            return -1;

        int parent = findParent(n, root);
        if (parent == -1) return -1;

        Son sibling = mem[parent].son;
        while (sibling.index != n)
            sibling = sibling.next;
        if (sibling.next == null)
            return -1;

        return sibling.next.index;
    }

    @Override
    public char label(int n) {
        if (n == root)
            return mem[root].label;
        if (findParent(n, root()) == -1)
            throw new NoSuchElementException();
        return mem[n].label;
    }

    @Override
    public Tree create(char v) {
        if (SPACE == -1)
            return this;
        root = SPACE;
        SPACE = mem[SPACE].next;
        mem[root].label = v;
        return this;
    }

    @Override
    public int root() {
        return root;
    }

    //обратный проход
    @Override
    public void makeNull(int root) {
        Node cur = mem[root];

        if (cur.son != null)
            makeNull(cur.son.index);
        else {
            mem[root].son = null;
            mem[root].next = SPACE;
            SPACE = root;
            return;
        }

        Son sibling = cur.son.next;
        while (sibling != null) {
            makeNull(sibling.index);
            sibling = sibling.next;
        }

        mem[root].son = null;
        mem[root].next = SPACE;
        SPACE = root;
    }

    @Override
    public Tree create(char v, Tree... t) {
        if (SPACE == -1)
            return null;

        root = SPACE;
        SPACE = mem[SPACE].next;
        mem[root].label = v;
        Son cur = null;
        for (int i = t.length - 1; i >= 0; i--) {
            cur = new Son(t[i].root, cur);
            t[i] = null;
        }
        mem[root].son = cur;
        return this;
    }

    @Override
    public Tree create(char v, Tree t1, Tree t2) {
        if (this == t1 && this == t2) return this;
        else if (root != -1)
            throw new IllegalArgumentException();

        if (SPACE == -1)
            return this;
        root = SPACE;
        SPACE = mem[SPACE].next;
        mem[root].label = v;
        mem[root].son = new Son(t1.root, new Son(t2.root));
        t1.root = -1;
        t1 = null;
        t2.root = -1;
        t2 = null;
        return this;
    }

    @Override
    public Tree create(char v, Tree t1) {
        if (root != -1)
            throw new IllegalArgumentException();

        if (this == t1) return create(v);

        if (SPACE == -1) return this;
        root = SPACE;
        SPACE = mem[SPACE].next;
        mem[root].label = v;
        mem[root].son = new Son(t1.root);
        t1.root = -1;
        t1 = null;
        return this;
    }

    //прямой проход
    public void directorder(int root) {
        Node cur = mem[root];

        System.out.print(cur.label);
        if (cur.son != null) {
            directorder(cur.son.index);
        } else return;

        Son sibling = cur.son.next;
        while (sibling != null) {
            directorder(sibling.index);
            sibling = sibling.next;
        }
        return;
    }

    //симметричный проход
    public void symmetric(int root) {
        Node cur = mem[root];
        if (cur.son != null)
            symmetric(cur.son.index);
        else {
            System.out.print(cur.label);
            return;
        }
        System.out.print(cur.label);
        Son sibling = cur.son.next;
        while (sibling != null) {
            symmetric(sibling.index);
            sibling = sibling.next;
        }
        return;
    }

    //обратный проход
    public void reverseoreder(int root) {
        Node cur = mem[root];

        if (cur.son != null)
            reverseoreder(cur.son.index);
        else {
            System.out.print(cur.label);
            return;
        }
        Son sibling = cur.son.next;
        while (sibling != null) {
            reverseoreder(sibling.index);
            sibling = sibling.next;
        }

        System.out.print(cur.label);
    }
}