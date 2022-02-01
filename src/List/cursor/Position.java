package List.cursor;

public class Position {

    private int p;

    public Position(int p) {
        this.p = p;
    }

    public int getPos() {
        return p;
    }

    public void setPos(int p) {
        this.p = p;
    }

    @Override
    public boolean equals(Object o) {

        Position position = (Position) o;
        return p == position.p;
    }
}
