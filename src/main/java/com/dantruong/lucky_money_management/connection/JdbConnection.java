package com.dantruong.lucky_money_management.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbConnection {
    // Cấu hình kết nối đến Aiven Cloud
    private static final String URL = "jdbc:mysql://mysql-1577d173-dantruong27102004-dfb3.i.aivencloud.com:25424/defaultdb?sslMode=REQUIRED";
    private static final String USER = "avnadmin";

    // SỬA LẠI DÒNG NÀY: Chỉ để tên biến, ví dụ là "DB_PASSWORD"
    // Code sẽ tự động ra ngoài môi trường (Docker hoặc máy tính) để tìm giá trị của biến này.
    private static String PASSWORD = System.getenv("DB_PASSWORD");

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Kiểm tra xem có đọc được mật khẩu không (chỉ dùng để debug, xóa khi chạy thật)
            if (PASSWORD == null || PASSWORD.isEmpty()) {
                System.err.println("Lỗi: Chưa cấu hình biến môi trường DB_PASSWORD!");
            }

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Lỗi: Không tìm thấy Driver MySQL!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Lỗi: Không thể kết nối đến Database Aiven!");
            e.printStackTrace();
        }
        return connection;
    }
}