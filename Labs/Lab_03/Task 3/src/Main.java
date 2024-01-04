import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alice", 85, 1));
        students.add(new Student("Bob", 92, 2));
        students.add(new Student("Charlie", 78, 1));
        students.add(new Student("David", 88, 2));
        students.add(new Student("Eve", 75, 3));

        Map<Integer, Double> averageScoresByCourse = students.stream()
                .collect(Collectors.groupingBy(Student::getCourse, Collectors.averagingDouble(Student::getScore)));

        averageScoresByCourse.forEach((course, averageScore) -> System.out.println("Course " + course + ": " + averageScore));
    }
}

class Student {
    private final String name;
    private final int score;
    private final int course;

    public Student(String name, int score, int course) {
        this.name = name;
        this.score = score;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getCourse() {
        return course;
    }
}
