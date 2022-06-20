package ManyToMany;

public class Course extends Entity {

    private final int name;
    Record students;

    public Course(int name) {
        this.name = name;
    }

    @Override
    boolean hasNext() {
        return false;
    }

    public int getName() {
        return name;
    }
}
