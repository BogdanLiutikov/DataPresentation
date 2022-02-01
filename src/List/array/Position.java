package List.array;

public class Position {

    private int pos;

    public Position(int pos) {
        this.pos = pos;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        Position position = (Position) o;
        return pos == position.pos;
    }
}
