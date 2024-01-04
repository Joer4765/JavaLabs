package org.example;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    String configFilename = "src/main/resources/config.properties";
    Properties properties = readConfiguration(configFilename);

    public static Connection getConnection(Properties properties) throws SQLException {
        return DriverManager.getConnection(
                properties.getProperty("database.url"), properties);
    }

    public static Properties readConfiguration(String filename) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(filename));
        } catch (IOException e) {
            System.out.println("Error! " + e.getMessage());
        }
        return properties;
    }

    @Test
    public void testCreateTeacher() throws SQLException {
        // Given
        Connection connection = getConnection(properties);
        TeacherDAO teacherDAO = new TeacherDAO(connection);
        Teacher teacher = new Teacher();
        teacher.setTeacherName("John Doe");

        // When
        teacherDAO.create(teacher);

        // Then
        assertNotNull(teacher.getTeacherId(), "Teacher ID should not be null after creation");

        teacherDAO.delete(teacher.getTeacherId());
    }

    @Test
    public void testReadTeacher() throws SQLException {
        // Given
        Connection connection = getConnection(properties);
        TeacherDAO teacherDAO = new TeacherDAO(connection);
        Teacher teacher = new Teacher();
        teacher.setTeacherName("Jane Doe");
        teacherDAO.create(teacher);

        // When
        Teacher retrievedTeacher = teacherDAO.read(teacher.getTeacherId());

        // Then
        assertNotNull(retrievedTeacher, "Retrieved teacher should not be null");
        assertEquals(teacher.getTeacherName(), retrievedTeacher.getTeacherName(), "Teacher names should match");

        teacherDAO.delete(teacher.getTeacherId());
    }

    @Test
    public void testUpdateTeacher() throws SQLException {
        // Given
        Connection connection = getConnection(properties);
        TeacherDAO teacherDAO = new TeacherDAO(connection);
        Teacher teacher = new Teacher();
        teacher.setTeacherName("Bob");
        teacherDAO.create(teacher);

        // When
        teacher.setTeacherName("Bob Updated");
        teacherDAO.update(teacher);

        // Then
        Teacher updatedTeacher = teacherDAO.read(teacher.getTeacherId());
        assertEquals("Bob Updated", updatedTeacher.getTeacherName(), "Teacher name should be updated");

        teacherDAO.delete(teacher.getTeacherId());
    }

    @Test
    public void testDeleteTeacher() throws SQLException {
        // Given
        Connection connection = getConnection(properties);
        TeacherDAO teacherDAO = new TeacherDAO(connection);
        Teacher teacher = new Teacher();
        teacher.setTeacherName("Mark");
        teacherDAO.create(teacher);

        // When
        teacherDAO.delete(teacher.getTeacherId());

        // Then
        Teacher deletedTeacher = teacherDAO.read(teacher.getTeacherId());
        assertNull(deletedTeacher, "Deleted teacher should be null");

        teacherDAO.delete(teacher.getTeacherId());
    }

    @Test
    public void testSearchTeacher() throws SQLException {
        // Given
        Connection connection = getConnection(properties);
        TeacherDAO teacherDAO = new TeacherDAO(connection);
        Teacher teacher1 = new Teacher();
        teacher1.setTeacherName("Alice");
        teacherDAO.create(teacher1);

        Teacher teacher2 = new Teacher();
        teacher2.setTeacherName("Alice");
        teacherDAO.create(teacher2);

        // When
        List<Teacher> foundTeachers = teacherDAO.searchByName("Alice");

        // Then
        assertEquals(2, foundTeachers.size(), "Should find two teachers with the name 'Alice'");

        teacherDAO.delete(teacher1.getTeacherId());
        teacherDAO.delete(teacher2.getTeacherId());
    }
}

