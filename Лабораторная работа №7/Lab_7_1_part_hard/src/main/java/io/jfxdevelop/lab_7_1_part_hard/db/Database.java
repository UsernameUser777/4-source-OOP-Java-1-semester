package io.jfxdevelop.lab_7_1_part_hard.db;

import io.jfxdevelop.lab_7_1_part_hard.model.Order;
import io.jfxdevelop.lab_7_1_part_hard.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/lab7";
    private static final String USER = "root";
    private static final String PASSWORD = "rootroot";

    public static User getUserWithOrders(int userId) {
        User user = null;
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement userStmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
            userStmt.setInt(1, userId);
            ResultSet userRs = userStmt.executeQuery();

            if (userRs.next()) {
                user = new User(userRs.getInt("id"), userRs.getString("name"));

                PreparedStatement orderStmt = conn.prepareStatement("SELECT * FROM orders WHERE user_id = ?");
                orderStmt.setInt(1, userId);
                ResultSet orderRs = orderStmt.executeQuery();

                while (orderRs.next()) {
                    Order order = new Order(orderRs.getInt("id"), orderRs.getString("description"));
                    user.addOrder(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
