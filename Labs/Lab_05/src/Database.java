import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String COURSES_FILE = "courses.dat";

    public static void saveCourses(List<Course> courses) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(COURSES_FILE))) {
            outputStream.writeObject(courses);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Course> loadCourses() {
        List<Course> courses = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(COURSES_FILE))) {
            courses = (List<Course>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public static void addCourse(Course course) {
        List<Course> courses = loadCourses();
        courses.add(course);
        saveCourses(courses);
    }

    public static void deleteCourse(Course course) {
        List<Course> courses = loadCourses();

        int index = -1;
        for (int i = 0; i < courses.size(); i++) {
            Course existingCourse = courses.get(i);
            if (existingCourse.getLectureHours() == course.getLectureHours() &&
                    existingCourse.getPracticalHours() == course.getPracticalHours() &&
                    existingCourse.getLabHours() == course.getLabHours()) {
                index = i;
                break;
            }
        }

        if (index >= 0) {
            courses.remove(index);
            saveCourses(courses);
            System.out.println("Курс видалено.");


            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(COURSES_FILE, false))) {
                outputStream.writeObject(courses);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Курс не знайдено.");
        }
    }

    public static String toString(List<Course> courses){
        int i = 0;
        StringBuilder s = new StringBuilder();
        for (Course course : courses) {
            i++;
            s.append("Course ").append(i).append('\n');
            s.append("Lecture Hours: ").append(course.getLectureHours()).append('\n');
            s.append("Practical Hours: ").append(course.getPracticalHours()).append('\n');
            s.append("Lab Hours: ").append(course.getLabHours()).append("\n\n");
        }
        return String.valueOf(s);
    }

}


class Database_TXT {
    private static final String COURSES_FILE = "courses.txt";

    public static void saveCourses( List<Course> courses) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(COURSES_FILE))) {
            for (Course course : courses) {
                writer.println(course.getLectureHours() + "," + course.getPracticalHours() + "," + course.getLabHours());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Course> loadCourses() {
        List<Course> courses = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(COURSES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int lectureHours = Integer.parseInt(parts[0]);
                    int practicalHours = Integer.parseInt(parts[1]);
                    int labHours = Integer.parseInt(parts[2]);
                    courses.add(new Course(lectureHours, practicalHours, labHours));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public static void addCourse(Course course) {
        List<Course> courses = loadCourses();
        courses.add(course);
        saveCourses(courses);
    }

    public static void deleteCourse(Course course) {
        List<Course> courses = loadCourses();
        boolean removed = courses.removeIf(existingCourse ->
                existingCourse.getLectureHours() == course.getLectureHours() &&
                        existingCourse.getPracticalHours() == course.getPracticalHours() &&
                        existingCourse.getLabHours() == course.getLabHours()
        );

        if (removed) {
            saveCourses(courses);
            System.out.println("Course deleted.");
        } else {
            System.out.println("Course not found.");
        }
    }

    public static String toString(List<Course> courses){
        int i = 0;
        StringBuilder s = new StringBuilder();
        for (Course course : courses) {
            i++;
            s.append("Course ").append(i).append('\n');
            s.append("Lecture Hours: ").append(course.getLectureHours()).append('\n');
            s.append("Practical Hours: ").append(course.getPracticalHours()).append('\n');
            s.append("Lab Hours: ").append(course.getLabHours()).append("\n\n");
        }
        return String.valueOf(s);
    }

}
