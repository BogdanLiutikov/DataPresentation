package ManyToMany;

public class Record extends Entity {

    Entity student;
    Entity course;

    public Record(Entity student, Entity course) {
        this.student = student;
        this.course = course;
    }

    @Override
    boolean hasNext() {
        return true;
    }
}
