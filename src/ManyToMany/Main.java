package ManyToMany;

public class Main {
    public static void main(String[] args) {

        Student[] students = new Student[4];
        Course[] courses = new Course[4];

        Relation DB = new Relation();
        DB.init(students, courses);

        init(students, courses);

        DB.subscribe("Bob", 2);
        DB.subscribe("Alice", 2);
        DB.subscribe("Alice", 1);
        DB.subscribe("Alice", 3);

        DB.getAllCourses("Bob");
        DB.getAllCourses("Alice");

//        DB.unSubscribe("Bob", 2);

        DB.getAllCourses("Bob");
        DB.getAllCourses("Alice");

        DB.getAllMembers(2);
        DB.getAllMembers(4);

        DB.unSubscribeAllStudent(2);
        DB.getAllMembers(2);
        DB.getAllCourses("Alice");
        DB.unSubscribeFromAllCourse("Alice");
        DB.getAllCourses("Alice");

    }

    private static void init(Student[] students, Course[] courses) {
        students[0] = new Student("Bob");
        students[1] = new Student("Alice");
        students[2] = new Student("Mary");
        students[3] = new Student("John");
        courses[0] = new Course(1);
        courses[1] = new Course(2);
        courses[2] = new Course(3);
        courses[3] = new Course(4);
    }
}
