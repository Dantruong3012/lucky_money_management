package com.dantruong.lucky_money_management.controller;

import com.dantruong.lucky_money_management.mapper.UserMapper;
import com.dantruong.lucky_money_management.model.dao.TransactionDao;
import com.dantruong.lucky_money_management.model.dao.UserDao;
import com.dantruong.lucky_money_management.model.dto.TransactionDto;
import com.dantruong.lucky_money_management.model.dto.UserDto;
import com.dantruong.lucky_money_management.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "home", value = "/dashboard")
public class DashBoardController extends HttpServlet {

    private TransactionDao transactionDao = new TransactionDao();
    private UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Integer userID = (Integer) session.getAttribute("currentUserId");

        // [BẢO VỆ 1] Nếu chưa đăng nhập -> Đuổi về trang Login
        if (userID == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        // Lấy thông tin User
        User user = userDao.findUserByUserID(userID);
        UserDto userDto = new UserDto();
        if (user != null) {
            userDto = UserMapper.userToDto(user);
        }

        // Lấy thông tin tiền nong
        BigDecimal totalIncome = transactionDao.getTotalAmount(userID, "INCOME");
        BigDecimal totalExpense = transactionDao.getTotalAmount(userID, "EXPENSE");

        // [BẢO VỆ 2] Xử lý trường hợp tài khoản mới chưa có tiền (tránh bị null)
        if (totalIncome == null) totalIncome = BigDecimal.ZERO;
        if (totalExpense == null) totalExpense = BigDecimal.ZERO;

        // Tính số dư (Lúc này chắc chắn không bị lỗi null nữa)
        BigDecimal balances = totalIncome.subtract(totalExpense);

        // Gửi dữ liệu sang JSP
        req.setAttribute("totalIncome", totalIncome);
        req.setAttribute("totalExpense", totalExpense);
        req.setAttribute("balances", balances);

        // Gửi tên hiển thị (nếu userDto rỗng thì để mặc định là "Bạn")
        req.setAttribute("displayName", (userDto.getName() != null) ? userDto.getName() : "Bạn");


        // 1. Gọi DAO lấy danh sách thống kê
        List<TransactionDto> chartStats = transactionDao.getChartStats(userID);

// 2. Chuẩn bị các chuỗi dữ liệu
        StringBuilder labels = new StringBuilder(); // Ngày tháng
        StringBuilder dataIncome = new StringBuilder();
        StringBuilder dataExpense = new StringBuilder();
        StringBuilder dataBalance = new StringBuilder();

// Format ngày tháng cho đẹp (dd/MM)
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
        BigDecimal currentBalance = BigDecimal.ZERO; // Cộng dồn số dư

        for (int i = 0; i < chartStats.size(); i++) {
            TransactionDto dto = chartStats.get(i);

            // Tính số dư tích lũy: Cộng dồn = Cũ + Thu - Chi
            currentBalance = currentBalance.add(dto.getIncome()).subtract(dto.getExpense());

            // Nối chuỗi
            labels.append("'").append(sdf.format(dto.getDate())).append("'");
            dataIncome.append(dto.getIncome());
            dataExpense.append(dto.getExpense());
            dataBalance.append(currentBalance);

            // Thêm dấu phẩy nếu chưa phải phần tử cuối
            if (i < chartStats.size() - 1) {
                labels.append(",");
                dataIncome.append(",");
                dataExpense.append(",");
                dataBalance.append(",");
            }
        }

// 3. Gửi sang JSP
        req.setAttribute("chartLabels", labels.toString());
        req.setAttribute("chartIncome", dataIncome.toString());
        req.setAttribute("chartExpense", dataExpense.toString());
        req.setAttribute("chartBalance", dataBalance.toString());

        req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
    }
}