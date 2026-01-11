package com.dantruong.lucky_money_management.controller;

import com.dantruong.lucky_money_management.model.dao.RegisterDao;
import com.dantruong.lucky_money_management.model.entity.User;
import com.dantruong.lucky_money_management.util.PasswordUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "register", value = "/register")
public class RegisterServlet extends HttpServlet {

    private RegisterDao registerDao = new RegisterDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        // 1. Lấy dữ liệu
        String userName = req.getParameter("username");
        String displayName = req.getParameter("displayname");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm_password");
        String securityCode = req.getParameter("security_code");

        String error = null;

        // 2. Validate dữ liệu
        if (userName == null || userName.trim().isEmpty()){
            error = "Tên đăng nhập không được để trống!";
        } else if (password == null || password.trim().isEmpty()) {
            error = "Mật khẩu không được để trống!";
        } else if (registerDao.isUserNameExist(userName)){
            error = "Tên tài khoản này đã có người sử dụng!";
        } else if (!password.equals(confirmPassword)) {
            error = "Mật khẩu xác nhận không khớp!";
        } else if (displayName == null || displayName.trim().isEmpty()){
            error = "Tên hiển thị không được bỏ trống";
        } else if (securityCode == null || securityCode.trim().isEmpty()){
            error = "Mã bảo mật không được bỏ trống";
        }

        // 3. Xử lý Lỗi (Nếu có)
        if (error != null){
            req.setAttribute("error", error);

            // Giữ lại dữ liệu cũ
            req.setAttribute("oldUserName", userName);
            req.setAttribute("oldDisplayName", displayName);
            req.setAttribute("oldSecurityCode", securityCode);

            req.getRequestDispatcher("/register.jsp").forward(req,resp);
            return;
        }

        // 4. Xử lý Thành công
        String hashedPassword = PasswordUtil.hashPassword(password);
        User user = new User(userName, hashedPassword, displayName, securityCode);

        if (registerDao.registerUser(user)){
            // Thành công -> Hiện thông báo trên trang Register
            String alert = "Bạn đã đăng ký tài khoản thành công! Hãy quay lại đăng nhập.";
            req.setAttribute("alert", alert);
            
            req.getRequestDispatcher("/register.jsp").forward(req, resp);

        } else {
            req.setAttribute("error", "Đăng ký thất bại do lỗi hệ thống (hoặc trùng lặp dữ liệu)!");

            req.setAttribute("oldUserName", userName);
            req.setAttribute("oldDisplayName", displayName);
            req.setAttribute("oldSecurityCode", securityCode);

            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }
    }
}