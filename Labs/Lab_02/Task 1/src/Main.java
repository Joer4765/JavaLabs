import java.util.*;

class Course {
    private final int lectureHours;
    private final int practicalHours;
    private final int labHours;

    public Course(int lectureHours, int practicalHours, int labHours) {
        this.lectureHours = lectureHours;
        this.practicalHours = practicalHours;
        this.labHours = labHours;
    }

    public int getLectureHours() {
        return this.lectureHours;
    }

    public int getPracticalHours() {
        return practicalHours;
    }

    public int getLabHours() {
        return labHours;
    }
}

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

    // Ґеттери та сеттери

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

// Клас для викладача
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

// Клас для студента
class Student implements Person {
    private final String name;
    private Map<Subject, Integer> grades;

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

    // Метод для обчислення середнього балу
    public double calculateAverageGrade() {
        int totalPoints = 0;
        int numSubjects = grades.size();
        for (int grade : grades.values()) {
            totalPoints += grade;
        }
        return (double) totalPoints / numSubjects;
    }
}

// Клас для групи студентів
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

    // Сортування студентів за рейтингом (середнім балом)
    public void sortByRating() {
        students.sort(Comparator.comparingDouble(Student::calculateAverageGrade).reversed());
    }
}


public class Main {
    public static void main(String[] args) {
        StudentGroup group = getStudentGroup();

        // Сортування студентів за рейтингом
        group.sortByRating();

        // Виведення інформації про студентів та їх рейтинг
        System.out.println("Група: " + group.getGroupName());
        System.out.println("Куратор: " + group.getCurator().getName());
        System.out.println("Список студентів за рейтингом:");
        for (Student student : group.getStudents()) {
            System.out.println(student.getName() + ": " + student.calculateAverageGrade());
        }
    }

    private static StudentGroup getStudentGroup() {
        Teacher teacher1 = new Teacher("Викладач 1");
        Teacher teacher2 = new Teacher("Викладач 2");

        Subject subject1 = new Subject("Предмет 1", 30, 15, 10, "екзамен", 1);
        Subject subject2 = new Subject("Предмет 2", 20, 20, 5, "залік", 2);

        Student student1 = new Student("Студент 1");
        student1.addGrade(subject1, 90);
        student1.addGrade(subject2, 85);

        Student student2 = new Student("Студент 2");
        student2.addGrade(subject1, 75);
        student2.addGrade(subject2, 92);

        StudentGroup group = new StudentGroup("Група 1", teacher1);
        group.addStudent(student1);
        group.addStudent(student2);
        return group;
    }
}
