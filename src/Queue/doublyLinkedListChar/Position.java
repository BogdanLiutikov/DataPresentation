package Queue.doublyLinkedListChar;

import Queue.doublyLinkedListChar.List.Node;

public class Position {

    private Node node;

    public Position(Node node) {
        this.node = node;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null)
            return node == null;
        Position p = (Position) o;
        return node == p.node;
    }
}
