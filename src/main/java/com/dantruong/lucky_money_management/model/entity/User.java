package com.dantruong.lucky_money_management.model.entity;

public class User {
    private Integer id;
    private String username;
    private String password;
    private String displayName;
    private String securityCode; // Trường mới dùng cho Quên Mật Khẩu

    // 1. Constructor rỗng
    public User() {
    }

    // 2. Constructor đầy đủ (Dùng khi lấy User từ Database lên, ví dụ lúc Login)
    public User(Integer id, String username, String password, String displayName, String securityCode) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.securityCode = securityCode;
    }

    // 3. Constructor tạo mới (Dùng khi Đăng Ký - chưa có ID)
    public User(String username, String password, String displayName, String securityCode) {
        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.securityCode = securityCode;
    }

    // --- GETTER & SETTER ---

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", displayName='" + displayName + '\'' +
                '}';
    }
}