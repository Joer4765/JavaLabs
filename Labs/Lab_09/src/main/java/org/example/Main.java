package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static Properties readConfiguration(String filename) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(filename));
        } catch (IOException e) {
            System.out.println("Error! " + e.getMessage());
        }
        return properties;
    }

    private static void saveTeachersToJson(List<Teacher> teachers, String jsonFile) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(jsonFile), teachers);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        try {
            String configFilename = "src/main/resources/config.properties";
            System.setProperty("log4j.configurationFile", "src/main/resources/log4j2.properties");
            Properties properties = readConfiguration(configFilename);
            System.out.println(properties);

            Connection connection = MysqlConnector.getConnection(properties);
            TeacherDAO teacherDAO = new TeacherDAO(connection);
            run(teacherDAO);

            List<Teacher> teachers = teacherDAO.readAll();
            saveTeachersToJson(teachers, "json/teachers.json");
            connection.close();
        } catch (SQLException | IOException e) {
            logger.error("An error occurred: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        } finally {
            logger.info("Application shutting down...");
        }
    }

    private static List<Teacher> readFromJson(String jsonFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(jsonFile), new TypeReference<>() {});
    }

    private static void run(TeacherDAO teacherDAO) throws SQLException, IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Choose an operation:");
            System.out.println("1. Create Teacher");
            System.out.println("2. Read Teacher");
            System.out.println("3. Update Teacher");
            System.out.println("4. Delete Teacher");
            System.out.println("5. Search Teacher by Name");
            System.out.println("6. Read from JSON");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter teacher name: ");
                    String createTeacherName = scanner.nextLine();
                    createTeacher(teacherDAO, createTeacherName);
                    break;
                case 2:
                    System.out.print("Enter teacher ID: ");
                    int readTeacherId = scanner.nextInt();
                    readTeacher(teacherDAO, readTeacherId);
                    break;
                case 3:
                    System.out.print("Enter teacher ID: ");
                    int updateTeacherId = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter new teacher name: ");
                    String updateTeacherName = scanner.nextLine();
                    updateTeacher(teacherDAO, updateTeacherId, updateTeacherName);
                    break;
                case 4:
                    System.out.print("Enter teacher ID: ");
                    int deleteTeacherId = scanner.nextInt();
                    deleteTeacher(teacherDAO, deleteTeacherId);
                    break;
                case 5:
                    System.out.print("Enter teacher name: ");
                    String searchTeacherName = scanner.nextLine();
                    searchTeacher(teacherDAO, searchTeacherName);
                    break;
                case 6: // Add case for reading teacher from JSON
                    List<Teacher> jsonTeachers = readFromJson("json/teachers.json");
                    System.out.println();
                    for (Teacher teacher : jsonTeachers) {
                        System.out.println("Teacher ID: " + teacher.getTeacherId());
                        System.out.println("Teacher Name: " + teacher.getTeacherName());
                        System.out.println("---------------");
                    }
                    System.out.println();
                    break;
                case 7:
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                    break;
            }
        }
    }

    private static void createTeacher(TeacherDAO teacherDAO, String teacherName) throws SQLException {
        System.out.println();
        Teacher teacher = new Teacher();
        teacher.setTeacherName(teacherName);
        teacherDAO.create(teacher);
        System.out.println("Teacher created with ID: " + teacher.getTeacherId());
        System.out.println();
    }

    private static void readTeacher(TeacherDAO teacherDAO, int teacherId) throws SQLException {
        Teacher teacher = teacherDAO.read(teacherId);
        if (teacher != null) {
            System.out.println("Teacher ID: " + teacher.getTeacherId());
            System.out.println("Teacher Name: " + teacher.getTeacherName());
        } else {
            System.out.println("Teacher not found.");
        }
        System.out.println();
    }

    private static void updateTeacher(TeacherDAO teacherDAO, int teacherId, String newTeacherName) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setTeacherId(teacherId);
        teacher.setTeacherName(newTeacherName);
        teacherDAO.update(teacher);
        System.out.println();
        System.out.println("Teacher updated successfully.");
        System.out.println();
    }

    private static void deleteTeacher(TeacherDAO teacherDAO, int teacherId) throws SQLException {
        teacherDAO.delete(teacherId);
        System.out.println();
        System.out.println("Teacher deleted successfully.");
        System.out.println();
    }

    private static void searchTeacher(TeacherDAO teacherDAO, String teacherName) throws SQLException {
        System.out.println();
        List<Teacher> teachers = teacherDAO.searchByName(teacherName);
        if (!teachers.isEmpty()) {
            Teacher teacher = teachers.get(0);
            System.out.println("Teacher ID: " + teacher.getTeacherId());
            System.out.println("Teacher Name: " + teacher.getTeacherName());
        } else {
            System.out.println("Teacher not found.");
        }
        System.out.println();
    }
}