import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

class Subject extends Course {
    private final String name;
    private final String evaluationType;
    private final int semester;

    public Subject(String name, int lectureHours, int practicalHours, int labHours, String evaluationType, int semester) {
        super(lectureHours, practicalHours, labHours);
        this.name = name;
        this.evaluationType = evaluationType;
        this.semester = semester;
    }

    public String getName() {
        return name;
    }

    public String getEvaluationType() {
        return evaluationType;
    }

    public int getSemester() {
        return semester;
    }
}

interface Person {
    String getName();
}

class Teacher implements Person {
    private final String name;

    public Teacher(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}

class Student implements Person {
    private final String name;
    private final Map<Subject, Integer> grades;

    public Student(String name) {
        this.name = name;
        this.grades = new HashMap<>();
    }

    public void addGrade(Subject subject, int grade) {
        grades.put(subject, grade);
    }

    @Override
    public String getName() {
        return name;
    }

    public Map<Subject, Integer> getGrades() {
        return grades;
    }

    public double calculateAverageGrade() {
        int totalPoints = 0;
        int numSubjects = grades.size();
        for (int grade : grades.values()) {
            totalPoints += grade;
        }
        return (double) totalPoints / numSubjects;
    }
}

class StudentGroup {
    private final String groupName;
    private final Teacher curator;
    private final List<Student> students;

    public StudentGroup(String groupName, Teacher curator) {
        this.groupName = groupName;
        this.curator = curator;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public String getGroupName() {
        return groupName;
    }

    public Teacher getCurator() {
        return curator;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void sortByRating() {
        students.sort(Comparator.comparingDouble(Student::calculateAverageGrade).reversed());
    }


    public static void main(String[] args) {
        Course course1 = new Course(30, 20, 10);
        Course course2 = new Course(40, 25, 15);
        Course course3 = new Course(40, 15, 20);

        List<Course> courses = new ArrayList<>();
        courses.add(course1);
        courses.add(course2);

        System.out.println("Text file:");

        Database_TXT.saveCourses(courses);
        List<Course> loadedCourses_txt = Database_TXT.loadCourses();
        System.out.println(Database_TXT.toString(loadedCourses_txt));
        Database_TXT.addCourse(course3);
        System.out.println(Database_TXT.toString(Database_TXT.loadCourses()));
        Database_TXT.deleteCourse(course3);
        System.out.println(Database_TXT.toString(Database_TXT.loadCourses()));

        System.out.println("Serializable (binary):");

        Database.saveCourses(courses);
        List<Course> loadedCourses = Database.loadCourses();
        System.out.println(Database.toString(loadedCourses));
        Database.addCourse(course3);
        System.out.println(Database.toString(Database.loadCourses()));
        Database.deleteCourse(course3);
        System.out.println(Database.toString(Database.loadCourses()));

    }

}
