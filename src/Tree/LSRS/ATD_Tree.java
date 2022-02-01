package Tree.LSRS;

public interface ATD_Tree {

    public int parent(int n);
    public int leftMostChild(int n);
    public int rightSibling(int n);
    public char label(int n);
    public Tree create(char v);
    public Tree create(char v, Tree t1);
    public Tree create(char v, Tree t1, Tree t2);
    public Tree create(char v, Tree ... t);
    public int root();
    public void makeNull(int root);

}
