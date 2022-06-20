package PartiallyOrderedSet;

public class Element extends Trail {
    int value;
    int count;

    public Element(int value) {
        super(null, null);
        this.value = value;
        this.count = 0;
    }
}
