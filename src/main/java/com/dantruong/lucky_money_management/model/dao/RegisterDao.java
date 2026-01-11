package com.dantruong.lucky_money_management.model.dao;

import com.dantruong.lucky_money_management.connection.JdbConnection;
import com.dantruong.lucky_money_management.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterDao {

    public boolean registerUser(User user) {
        String sql = "INSERT INTO users (username, password, display_name, security_code) VALUES (?,?,?,?)";
        try (Connection connection = JdbConnection.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getDisplayName());
            ps.setString(4, user.getSecurityCode());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    // kiem tra xem user da ton tai chua

    public  boolean isUserNameExist(String userName){
        String sql = "SELECT COUNT(1) FROM users WHERE username = ?";
        try(Connection connection = JdbConnection.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return rs.getInt(1) > 0;
            }
        }catch (SQLException e){e.printStackTrace();}
        return false;
    }
}
