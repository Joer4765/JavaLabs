import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    public static Properties readConfiguration(String filename) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(filename));
        } catch (IOException e) {
            System.out.println("Error! " + e.getMessage());
        }
        return properties;
    }

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String configFilename = "./resources/config.properties";
            Properties properties = readConfiguration(configFilename);
            System.out.println(properties);

            Connection connection = MysqlConnector.getConnection(properties);

            Run(connection);

            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void Run(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Choose an operation:");
            System.out.println("1. Create Teacher");
            System.out.println("2. Read Teacher");
            System.out.println("3. Update Teacher");
            System.out.println("4. Delete Teacher");
            System.out.println("5. Search Subjects by Teacher");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter teacher name: ");
                    String createTeacherName = scanner.nextLine();
                    createTeacher(connection, createTeacherName);
                    break;
                case 2:
                    System.out.print("Enter teacher name: ");
                    String readTeacherName = scanner.nextLine();
                    int readTeacherId = getTeacherId(connection, readTeacherName);
                    break;
                case 3:
                    System.out.print("Enter teacher ID: ");
                    int updateTeacherId = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter new teacher name: ");
                    String updateTeacherName = scanner.nextLine();
                    updateTeacher(connection, updateTeacherId, updateTeacherName);
                    break;
                case 4:
                    System.out.print("Enter teacher ID: ");
                    int deleteTeacherId = scanner.nextInt();
                    deleteTeacher(connection, deleteTeacherId);
                    break;
                case 5:
                    System.out.print("Enter teacher name: ");
                    String searchTeacherName = scanner.nextLine();
                    searchSubjectsByTeacher(connection, searchTeacherName);
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                    break;
            }
        }
    }

    private static void createTeacher(Connection connection, String teacherName) throws SQLException {
        String insertQuery = "INSERT INTO Teachers (teacher_name) VALUES (?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, teacherName);
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                System.out.println("Teacher created with ID: " + generatedKeys.getInt(1));
            }
            System.out.println();
        }
    }

    private static int getTeacherId(Connection connection, String teacherName) throws SQLException {
        int teacherId = -1;
        String selectQuery = "SELECT teacher_id FROM Teachers WHERE teacher_name = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, teacherName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                teacherId = resultSet.getInt("teacher_id");
                System.out.println("Teacher ID: " + teacherId);
            } else {
                System.out.println("Teacher not found.");
            }
            System.out.println();
        }

        return teacherId;
    }

    private static void updateTeacher(Connection connection, int teacherId, String newTeacherName) throws SQLException {
        String updateQuery = "UPDATE Teachers SET teacher_name = ? WHERE teacher_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, newTeacherName);
            preparedStatement.setInt(2, teacherId);
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Teacher updated successfully.");
            } else {
                System.out.println("Teacher not found.");
            }
            System.out.println();
        }
    }

    private static void deleteTeacher(Connection connection, int teacherId) throws SQLException {
        String deleteQuery = "DELETE FROM Teachers WHERE teacher_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, teacherId);
            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Teacher deleted successfully.");
            } else {
                System.out.println("Teacher not found.");
            }
            System.out.println();
        }
    }

    private static void searchSubjectsByTeacher(Connection connection, String teacherName) throws SQLException {
        String searchQuery = "SELECT * FROM Subjects s JOIN Teachers t ON s.teacher_id = t.teacher_id WHERE t.teacher_name = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(searchQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            preparedStatement.setString(1, teacherName);
            ResultSet resultSet = preparedStatement.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Calculate column widths
            int[] columnWidths = new int[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnWidths[i - 1] = metaData.getColumnName(i).length();
            }

            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String value = resultSet.getString(i);
                    if (value != null && value.length() > columnWidths[i - 1]) {
                        columnWidths[i - 1] = value.length();
                    }
                }
            }

            System.out.println();

            // Print column headers
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                System.out.printf("%-" + (columnWidths[i - 1] + 2) + "s", columnName);
            }
            System.out.println();

            // Print data
            resultSet.beforeFirst(); // Reset result set to the beginning
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String value = resultSet.getString(i);
                    System.out.printf("%-" + (columnWidths[i - 1] + 2) + "s", value);
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}


import lombok.Data;

@Data
public class Teacher {
    private int teacherId;
    private String teacherName;
}

@Data
public class Subject {
    private int subjectId;
    private String subjectName;
    private int lectureHours;
    private Integer practicalHours; // Integer, якщо може бути null
    private Integer labHours; // Integer, якщо може бути null
    private String assessmentType;
    private int semester;
    private int teacherId;
}

@Data
public class StudentsGroup {
    private int groupId;
    private String groupName;
}

@Data
public class Curator {
    private int curatorId;
    private String curatorName;
    private int groupId;
}

@Data
public class Student {
    private int studentId;
    private String studentName;
    private int groupId;
}

@Data
public class StudentPerformance {
    private int performanceId;
    private int studentId;
    private int subjectId;
    private int score;
}
