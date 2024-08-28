package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // สร้าง instance แบบ static ของ DatabaseConnection
    private static DatabaseConnection instance;
    private static Connection connection;

    // ค่าคงที่สำหรับการเชื่อมต่อฐานข้อมูล
    private static final String URL = "jdbc:mysql://localhost:3306/fiction";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Constructor แบบ private เพื่อป้องกันการสร้าง instance ซ้ำ
    private DatabaseConnection() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new SQLException("Failed to create database connection.", e);
        }
    }

    // Method สำหรับการคืนค่า instance ของ DatabaseConnection
    public static DatabaseConnection getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (connection.isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // Method สำหรับการคืนค่า connection
    public Connection getConnection() {
        return connection;
    }
}
