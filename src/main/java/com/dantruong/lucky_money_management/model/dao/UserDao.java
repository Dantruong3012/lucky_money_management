package com.dantruong.lucky_money_management.model.dao;

import com.dantruong.lucky_money_management.connection.JdbConnection;
import com.dantruong.lucky_money_management.mapper.UserMapper;
import com.dantruong.lucky_money_management.model.dto.UserDto;
import com.dantruong.lucky_money_management.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public User findUserByUserID(int userId){
        String sql = "SELECT * FROM users WHERE id = ?";
        User user = new User();
        try(Connection connection = JdbConnection.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setDisplayName(rs.getString("display_name"));
                user.setSecurityCode(rs.getString("security_code"));
                return  user;
            }

        }catch (SQLException e){e.printStackTrace();}
        return null;
    }


    public User findUserByUserName(String usrName){
        User user = new User();
        String sql = "SELECT * FROM users WHERE username = ?";
        try(Connection connection = JdbConnection.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, usrName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setDisplayName(rs.getString("display_name"));
                user.setSecurityCode(rs.getString("security_code"));
                return  user;
            }
        }catch (SQLException e){e.printStackTrace();}
        return  null;
    }
}
