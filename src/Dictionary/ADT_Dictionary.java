package Dictionary;

public interface ADT_Dictionary {

    void insert(char[] value);
    void delete(char[] value);
    boolean member(char[] value);
    void makeNull();
}
