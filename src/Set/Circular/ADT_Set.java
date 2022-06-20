package Set.Circular;

 public interface ADT_Set {

    Set Intersection(Set other);
    Set Union(Set other);
    Set Difference(Set other);
    void Insert(int x);
    void Delete(int x);
    Set Assign(Set other);
    int Min();
    int Max();
    boolean Equal(Set other);
    void MakeNull();
    boolean Member(int x);
    Set Merge(Set other);
    Set Find(Set s, int x);
}
