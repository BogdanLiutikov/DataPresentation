package ManyToMany;

public class Student extends Entity {

    private final String name;
    Record courses;

    public Student(String name) {
        this.name = name;
    }

    @Override
    boolean hasNext() {
        return false;
    }

    public String getName() {
        return name;
    }
}
