package PartiallyOrderedSet;

public class Trail {
    Element id;
    Trail next;

    public Trail(Element id, Trail next) {
        this.id = id;
        this.next = next;
    }
}
