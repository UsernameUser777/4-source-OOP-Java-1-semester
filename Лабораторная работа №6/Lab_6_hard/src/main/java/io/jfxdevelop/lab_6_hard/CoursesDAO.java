package io.jfxdevelop.lab_6_hard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoursesDAO {
    private final Connection conn;

    public CoursesDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Course> getAll() throws SQLException {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT id, title, credits FROM courses ORDER BY id";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Course(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("credits")
                ));
            }
        }
        return list;
    }

    public void insert(String title, int credits) throws SQLException {
        String sql = "INSERT INTO courses (title, credits) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.setInt(2, credits);
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM courses WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
