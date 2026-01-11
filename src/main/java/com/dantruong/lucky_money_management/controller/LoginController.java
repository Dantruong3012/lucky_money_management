package com.dantruong.lucky_money_management.controller;

import com.dantruong.lucky_money_management.mapper.UserMapper;
import com.dantruong.lucky_money_management.model.dao.UserDao;
import com.dantruong.lucky_money_management.model.dto.UserDto;
import com.dantruong.lucky_money_management.model.entity.User;
import com.dantruong.lucky_money_management.util.PasswordUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "login", value = {"/login-servelet", ""})
public class LoginController extends HttpServlet {
    private UserDao userDao = new UserDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");

        String error = null;
        User userEntity = null;

        // 1. Kiểm tra nhập thiếu
        if (userName == null || userName.trim().isEmpty() || password == null || password.trim().isEmpty()){
            error = "Vui lòng nhập đầy đủ thông tin!";
        } else {
            // 2. Tìm user
            userEntity = userDao.findUserByUserName(userName);

            // 3. Logic kiểm tra an toàn (Tránh NullPointer)
            if (userEntity == null) {
                // Trường hợp 1: Không tìm thấy tài khoản
                error = "Tài khoản hoặc mật khẩu không đúng. Vui lòng thử lại";
            } else {
                // Trường hợp 2: Có tài khoản -> Mới check password
                boolean isPasswordMatched = PasswordUtil.checkPassword(password, userEntity.getPassword());
                if (!isPasswordMatched) {
                    error = "Tài khoản hoặc mật khẩu không đúng. Vui lòng thử lại";
                }
            }
        }

        // 4. Xử lý kết quả
        if (error != null) {
            // --- CÓ LỖI ---
            req.setAttribute("errorMessage", error);
            req.setAttribute("oldUsername", userName);
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        } else {
            // --- THÀNH CÔNG ---
            UserDto userDto = UserMapper.userToDto(userEntity);
            HttpSession session = req.getSession();

            // Lưu thông tin vào Session
            // (Nên lưu cả object DTO để trang chủ lấy tên hiển thị: Xin chào, A!)
            session.setAttribute("currentUser", userDto);
            session.setAttribute("currentUserId", userDto.getId());

            // [QUAN TRỌNG] Chuyển hướng về trang chủ
            resp.sendRedirect("dashboard");
        }
    }
}
