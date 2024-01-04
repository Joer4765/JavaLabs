import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO {
    private Connection conn;
    private static final String INSERT_TEACHER = "INSERT INTO Teachers (teacher_name) VALUES (?)";
    private static final String SELECT_TEACHER_BY_ID = "SELECT * FROM Teachers WHERE teacher_id = ?";
    private static final String SELECT_ALL_TEACHERS = "SELECT * FROM Teachers";
    private static final String UPDATE_TEACHER = "UPDATE Teachers SET teacher_name = ? WHERE teacher_id = ?";
    private static final String DELETE_TEACHER = "DELETE FROM Teachers WHERE teacher_id = ?";
    private static final String SEARCH_TEACHER_BY_NAME = "SELECT * FROM Teachers WHERE ? = ?";

    public TeacherDAO(Connection conn) {
        this.conn = conn;
    }

    public void create(TeacherDTO teacher) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(INSERT_TEACHER, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, teacher.getTeacherName());
            stmt.executeUpdate();
            ResultSet resultSet = stmt.getGeneratedKeys();
            if (resultSet.next()) {
                teacher.setTeacherId(resultSet.getInt(1));
            }
        }
    }

    public TeacherDTO read(int teacherId) throws SQLException {
        TeacherDTO teacher = null;
        try (PreparedStatement stmt = conn.prepareStatement(SELECT_TEACHER_BY_ID)) {
            stmt.setInt(1, teacherId);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                teacher = new TeacherDTO();
                teacher.setTeacherId(resultSet.getInt("teacher_id"));
                teacher.setTeacherName(resultSet.getString("teacher_name"));
            }
        }
        return teacher;
    }

    public List<TeacherDTO> readAll() throws SQLException {
        List<TeacherDTO> teachers = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(SELECT_ALL_TEACHERS);
            while (resultSet.next()) {
                TeacherDTO teacher = new TeacherDTO();
                teacher.setTeacherId(resultSet.getInt("teacher_id"));
                teacher.setTeacherName(resultSet.getString("teacher_name"));
                teachers.add(teacher);
            }
        }
        return teachers;
    }

    public void update(TeacherDTO teacher) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(UPDATE_TEACHER)) {
            stmt.setString(1, teacher.getTeacherName());
            stmt.setInt(2, teacher.getTeacherId());
            stmt.executeUpdate();
        }
    }

    public void delete(int teacherId) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(DELETE_TEACHER)) {
            stmt.setInt(1, teacherId);
            stmt.executeUpdate();
        }
    }

    public List<TeacherDTO> searchByName(String name) throws SQLException {
        List<TeacherDTO> result = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(SEARCH_TEACHER_BY_NAME)) {
            stmt.setString(1, name);
            stmt.setString(2, name);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                TeacherDTO teacher = new TeacherDTO();
                teacher.setTeacherId(resultSet.getInt("teacher_id"));
                teacher.setTeacherName(resultSet.getString("teacher_name"));
                result.add(teacher);
            }
        }
        return result;
    }
}
