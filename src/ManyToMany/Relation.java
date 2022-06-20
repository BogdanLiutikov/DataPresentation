package ManyToMany;

public class Relation {

    //Хеш таблицы хранят
    //Ключ - Имя курса/студента
    //Значение на регистрационную запись
    Student[] students;
    Course[] courses;

    public void init(Student[] students, Course[] courses) {
        this.students = students;
        this.courses = courses;
    }

    public void subscribe(String student, int course) {
        Student s = findStudentByName(student);
        Course c = findCourseByName(course);
        Record record = new Record(null, null);

        if (s.courses == null) {
            record.student = s;
        } else {
            if (isSubscribed(s, c))
                return;
            record.student = s.courses;
        }
        s.courses = record;

        if (c.students == null) {
            record.course = c;
        } else {
            if (isMember(s, c))
                return;
            record.course = c.students;
        }
        c.students = record;
    }

    public void unSubscribe(String student, int course) {
        Student s = findStudentByName(student);
        Course c = findCourseByName(course);

        if (!isSubscribed(s, c) || !isMember(s, c))
            return;

        Entity record = getRecord(s, c);

        removeRecord(record, s);
        removeRecord(record, c);
    }

    public void unSubscribeFromAllCourse(String student) {
        Student s = findStudentByName(student);

        if (s == null || s.courses == null)
            return;

        Entity record = s.courses;
        while (record.hasNext()) {
            Entity course = record;
            while (course.hasNext())
                course = ((Record) course).course;
            removeRecord(record, (Course) course);
            removeRecord(record, s);
            record = ((Record) record).student;
        }
    }

    public void unSubscribeAllStudent(int course) {
        Course c = findCourseByName(course);

        if (c == null || c.students == null)
            return;

        Entity record = c.students;
        while (record.hasNext()) {
            Entity student = record;
            while (student.hasNext())
                student = ((Record) student).student;
            removeRecord(record, (Student) student);
            removeRecord(record, c);
            record = ((Record) record).course;
        }
    }

    private Record getPrevRecord(Entity record, Student s) {
        Entity current = s.courses;
        Entity prev = null;
        while (current.hasNext()) {
            if (current == record)
                return (Record) prev;
            prev = current;
            current = ((Record) current).student;
        }
        return (Record) prev;
    }

    private Record getPrevRecord(Entity record, Course c) {
        Entity current = c.students;
        Entity prev = null;
        while (current.hasNext()) {
            if (current == record)
                return (Record) prev;
            prev = current;
            current = ((Record) current).course;
        }
        return (Record) prev;
    }

    private void removeRecord(Entity record, Student student) {
        if (student.courses == record) {
            if (student.courses.student.hasNext())
                student.courses = (Record) student.courses.student;
            else
                student.courses = null;
        } else {
            Record st = getPrevRecord(record, student);
            st.student = ((Record) st.student).student;
        }
    }

    private void removeRecord(Entity record, Course course) {
        if (course.students == record)
            if (course.students.course.hasNext())
                course.students = (Record) course.students.course;
            else
                course.students = null;
        else {
            Record cour = getPrevRecord(record, course);
            cour.course = ((Record) cour.course).course;
        }
    }

    private Entity getRecord(Student s, Course c) {
        Entity course = s.courses;
        while (course.hasNext()) {
            Entity student = course;
            while (student.hasNext())
                student = ((Record) student).course;
            if (((Course) student) == c)
                return course;

            course = ((Record) course).student;
        }
        return course;
    }

    private boolean isMember(Student s, Course c) {
        Entity record = c.students;
        while (record.hasNext()) {
            Entity student = record;
            while (student.hasNext())
                student = ((Record) student).student;
            if ((Student) student == s)
                return true;
            record = ((Record) record).course;
        }
        return false;
    }

    private boolean isSubscribed(Student s, Course c) {
        Entity record = s.courses;
        while (record.hasNext()) {
            Entity course = record;
            while (course.hasNext())
                course = ((Record) course).course;
            if ((Course) course == c)
                return true;
            record = ((Record) record).student;
        }
        return false;
    }

    public void getAllCourses(String name) {
        System.out.println("Курсы " + name + ":");
        Student s = findStudentByName(name);
        Entity record = s.courses;
        if (record == null) {
            System.out.println("Студент никуда не записан");
            return;
        }
        while (record.hasNext()) {
            Entity course = record;
            while (course.hasNext())
                course = ((Record) course).course;

            System.out.print(((Course) course).getName() + " ");

            record = ((Record) record).student;
        }
        System.out.println();
    }

    public void getAllMembers(int name) {
        Course c = findCourseByName(name);
        if (c == null)
            return;

        Entity record = c.students;
        if (record == null) {
            System.out.printf("На %d курс никто не записан\n", name);
            return;
        }

        System.out.println("Студенты записанные на курс " + c.getName());
        while (record.hasNext()) {
            Entity student = record;
            while (student.hasNext())
                student = ((Record) student).student;
            System.out.println(((Student) student).getName());
            record = ((Record) record).course;
        }
    }

    private Student findStudentByName(String name) {
        for (Student student : students) {
            if (name.equals(student.getName()))
                return student;
        }
        System.out.println("Студент не найден");
        return null;
    }

    private Course findCourseByName(int name) {
        for (Course course : courses) {
            if (name == course.getName())
                return course;
        }
        System.out.println("Курс не найден");
        return null;
    }
}