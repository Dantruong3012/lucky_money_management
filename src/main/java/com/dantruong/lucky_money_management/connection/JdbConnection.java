package com.dantruong.lucky_money_management.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbConnection {
    // Cấu hình kết nối đến Aiven Cloud
    // Định dạng: jdbc:mysql://[HOST]:[PORT]/[DB_NAME]?sslMode=REQUIRED
    private static final String URL = "jdbc:mysql://mysql-1577d173-dantruong27102004-dfb3.i.aivencloud.com:25424/defaultdb?sslMode=REQUIRED";

    private static final String USER = "avnadmin";

    private static final String PASSWORD = "YOUR_PASSWORD";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Tạo kết nối
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // (Tùy chọn) In ra dòng này để biết chắc chắn đã kết nối được
            // System.out.println(">> Đã kết nối thành công đến Aiven Cloud!");

        } catch (ClassNotFoundException e) {
            System.err.println("Lỗi: Không tìm thấy Driver MySQL! Bạn đã add thư viện mysql-connector chưa?");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Lỗi: Không thể kết nối đến Database Aiven! Kiểm tra lại đường truyền mạng.");
            e.printStackTrace();
        }
        return connection;
    }
}