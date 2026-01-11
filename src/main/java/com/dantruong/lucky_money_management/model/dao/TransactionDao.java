package com.dantruong.lucky_money_management.model.dao;

import com.dantruong.lucky_money_management.connection.JdbConnection;
import com.dantruong.lucky_money_management.model.dto.TransactionDto;
import com.dantruong.lucky_money_management.model.entity.Transaction;


import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao {

    public  boolean insertTransaction(Transaction transaction){
        String sql = "INSERT INTO transactions (user_id, giver_name, relationship, amount, type, wish) VALUES (?,?,?,?,?,?)";
        try(Connection connection = JdbConnection.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, transaction.getUserId());
            ps.setString(2, transaction.getGiverName());
            ps.setString(3, transaction.getRelationship());
            ps.setBigDecimal(4, transaction.getAmount());
            ps.setString(5, transaction.getType());
            ps.setString(6, transaction.getWish());
            return ps.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  false;
    }


    // Lấy Lịch sử thêm tiền lixi của từng user.

    public List<Transaction> findAllByUserId (int UserId){
        List<Transaction> list = new ArrayList<>();
        String sql = "select * from transactions where user_id = ? order by created_at desc";
        try(Connection connection = JdbConnection.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, UserId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Transaction t = new Transaction();
                t.setId(rs.getInt("id"));
                t.setUserId(rs.getInt("user_id"));
                t.setGiverName(rs.getString("giver_name"));
                t.setRelationship(rs.getString("relationship"));
                t.setAmount(rs.getBigDecimal("amount"));
                t.setType(rs.getString("type"));
                t.setWish(rs.getString("wish"));
                t.setCreatedTime(rs.getTimestamp("created_at"));
                list.add(t);

                // SAU REFACTOR LAI DUNG TRANSACTION DTO
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
        }


    // DELETE XOA LICH SU THEO USER_ID.
    public  boolean deleteTransactionById(int id) {
        String sql = "DELETE FROM transactions WHERE id = ?";
        try(Connection connection = JdbConnection.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  false;
    }


    // Ham Tinh Tong Tien

    public BigDecimal getTotalAmount(int userId, String type) {
        // 1. Khởi tạo mặc định là 0
        BigDecimal total = BigDecimal.ZERO;

        String sql = "SELECT SUM(amount) FROM transactions WHERE user_id = ? and type = ?";

        try (Connection connection = JdbConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setString(2, type);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // [SỬA LỖI 1]: Lấy cột số 1 (vì SELECT SUM chỉ trả về 1 cột)
                BigDecimal result = rs.getBigDecimal(1);

                // [SỬA LỖI 2]: Kiểm tra null và gán giá trị vào biến total để trả về
                if (result != null) {
                    total = result;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Trả về kết quả đã lấy được (hoặc 0 nếu lỗi/không có dữ liệu)
        return total;
    }

    // LAY MOT GIAO DICH DUNG KHI MUON SUA.

    public Transaction findById(int id){
        String sql = "SELECT * FROM transactions WHERE id = ?";
        try(Connection connection = JdbConnection.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Transaction(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("giver_name"),
                        rs.getString("relationship"),
                        rs.getBigDecimal("amount"),
                        rs.getString("type"),
                        rs.getString("wish"),
                        rs.getTimestamp("created_at")
                );
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }


    // LAY DU LIEU DE VE BIEU DO

    public  List<TransactionDto> getChartStats(int userID){
        List<TransactionDto> list = new ArrayList<>();
        // nhom sql theo ngay tinh tong thu chi rieng biet

        String sql = "SELECT DATE(created_at) as trans_date, " +
                "SUM(CASE WHEN type = 'INCOME' THEN amount ELSE 0 END) AS total_income, " +
                "SUM(CASE WHEN type = 'EXPENSE' THEN amount ELSE 0 END) as total_expense " +
                "FROM transactions " +
                "WHERE user_id = ? " +
                "GROUP BY DATE(created_at) " +
                "ORDER BY trans_date ASC LIMIT 10";

        try (Connection connection =  JdbConnection.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                TransactionDto dto = new TransactionDto();
                dto.setDate(rs.getTimestamp("trans_date"));
                dto.setIncome(rs.getBigDecimal("total_income"));
                dto.setExpense(rs.getBigDecimal("total_expense"));
                list.add(dto);
            }
        }catch (SQLException e){e.printStackTrace();}
        return list;
    }
    }


