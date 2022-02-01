package Tree.LSRS;

import java.util.NoSuchElementException;

public class Tree implements ATD_Tree {

    static class Node {
        private int leftMostChild; //левый сын
        private char label;//значение
        private int rightSibling; //правый брат + SPACE

        Node(int leftMostChild, char label, int rightSibling) {
            this.leftMostChild = leftMostChild;
            this.label = label;
            this.rightSibling = rightSibling;
        }

        Node() {
            this(-1, ' ', -1);
        }
    }

    private int root;
    private static final int size = 10;
    public static Node[] mem;
    private static int SPACE;

    static {
        mem = new Node[size];

        for (int i = 0; i < size - 1; i++) {
            mem[i] = new Node(-1, ' ', i + 1);
        }

        mem[size - 1] = new Node(-1, ' ', -1);
        SPACE = 0;
    }

    public Tree() {
        this.root = -1;
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

        if (cur.leftMostChild == -1)
            return -1;

        int leftMostChild = cur.leftMostChild;
        if (leftMostChild == n)
            return root;


        int p = findParent(n, cur.leftMostChild);
        if (p != -1)
            return p;


        int sibling = mem[cur.leftMostChild].rightSibling;
        while (sibling != -1) {
            if (sibling == n)
                return root;
            p = findParent(n, sibling);
            if (p != -1)
                return p;
            sibling = mem[sibling].rightSibling;
        }
        return -1;
    }

    @Override
    public int leftMostChild(int n) {
        if (n == root)
            return mem[root].leftMostChild;

        int parent = findParent(n, root);
        if (parent == -1 || mem[n].leftMostChild == -1) return -1;

        return mem[n].leftMostChild;
    }

    @Override
    public int rightSibling(int n) {
        if (n == root)
            return -1;
        int parent = findParent(n, root);
        if (parent == -1 || mem[n].rightSibling == -1) return -1;

        int sibling = mem[n].rightSibling;
        while (sibling != n)
            sibling = mem[sibling].rightSibling;

        if (mem[sibling].rightSibling == -1)
            return -1;

        return mem[sibling].rightSibling;
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
        SPACE = mem[SPACE].rightSibling;
        mem[root].leftMostChild = -1;
        mem[root].label = v;
        mem[root].rightSibling = -1;
        return this;
    }

    @Override
    public Tree create(char v, Tree t1) {
        if (root != -1)
            throw new IllegalArgumentException();

        if (this == t1) return create(v);

        if (SPACE == -1) return this;
        root = SPACE;
        SPACE = mem[SPACE].rightSibling;
        mem[root].leftMostChild = t1.root;
        mem[root].label = v;
        mem[root].rightSibling = -1;
        t1.root = -1;
        t1 = null;
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
        SPACE = mem[SPACE].rightSibling;
        mem[root].label = v;
        mem[root].leftMostChild = t1.root;
        mem[root].rightSibling = t2.root;
        t1.root = -1;
        t1 = null;
        t2.root = -1;
        t2 = null;
        return this;
    }

    @Override
    public Tree create(char v, Tree... t) {
        if (SPACE == -1)
            return null;

        root = SPACE;
        SPACE = mem[SPACE].rightSibling;
        mem[root].leftMostChild = t[0].root;
        mem[root].label = v;
        mem[root].rightSibling = -1;
        int cur;
        for (int i = 1; i < t.length; i++) {
            cur = t[i - 1].root;
            mem[cur].rightSibling = t[i].root;
            t[i - 1].root = -1;
        }
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

        if (cur.leftMostChild != -1)
            makeNull(cur.leftMostChild);
        else {
            mem[root].leftMostChild = -1;
            mem[root].rightSibling = SPACE;
            SPACE = root;
            return;
        }

        int sibling = cur.rightSibling;
        while (sibling != -1) {
            makeNull(sibling);
            sibling = mem[sibling].rightSibling;
        }

        mem[root].label = ' ';
        mem[root].leftMostChild = -1;
        mem[root].rightSibling = SPACE;
        SPACE = root;
    }
}
