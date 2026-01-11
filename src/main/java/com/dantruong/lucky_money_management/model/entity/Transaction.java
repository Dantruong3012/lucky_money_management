package com.dantruong.lucky_money_management.model.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Transaction {
    private Integer id;
    private Integer userId;       // [QUAN TRỌNG] ID của người dùng (để biết tiền của ai)
    private String giverName;     // [MỚI] Tên người gửi/nhận (Thay thế cho giverID cũ)
    private String relationship;  // [MỚI] Mối quan hệ (Người thân, Bạn bè...)
    private BigDecimal amount;
    private String type;          // [MỚI] Loại giao dịch: "INCOME" (Thu) hoặc "EXPENSE" (Chi)
    private String wish;
    private Timestamp createdTime;

    // 1. Constructor rỗng (Bắt buộc)
    public Transaction() {
    }

    // 2. Constructor đầy đủ (Dùng khi lấy dữ liệu từ DB lên)
    public Transaction(Integer id, Integer userId, String giverName, String relationship, BigDecimal amount, String type, String wish, Timestamp createdTime) {
        this.id = id;
        this.userId = userId;
        this.giverName = giverName;
        this.relationship = relationship;
        this.amount = amount;
        this.type = type;
        this.wish = wish;
        this.createdTime = createdTime;
    }

    // 3. Constructor thiếu ID và Time (Dùng khi tạo mới để Insert vào DB)
    public Transaction(Integer userId, String giverName, String relationship, BigDecimal amount, String type, String wish) {
        this.userId = userId;
        this.giverName = giverName;
        this.relationship = relationship;
        this.amount = amount;
        this.type = type;
        this.wish = wish;
    }

    // --- GETTER & SETTER ---

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getGiverName() {
        return giverName;
    }

    public void setGiverName(String giverName) {
        this.giverName = giverName;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWish() {
        return wish;
    }

    public void setWish(String wish) {
        this.wish = wish;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    // toString để debug cho dễ
    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", giverName='" + giverName + '\'' +
                ", amount=" + amount +
                ", wish='" + wish + '\'' +
                '}';
    }
}