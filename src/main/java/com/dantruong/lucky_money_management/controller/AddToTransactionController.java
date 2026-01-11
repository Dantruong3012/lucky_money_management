package com.dantruong.lucky_money_management.controller;

import com.dantruong.lucky_money_management.model.dao.TransactionDao;
import com.dantruong.lucky_money_management.model.entity.Transaction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "transaction-controller", value = "/TransactionServlet")
public class AddToTransactionController extends HttpServlet {

    private TransactionDao transactionDao = new TransactionDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/add-transaction.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        Integer userId = (Integer) session.getAttribute("currentUserId");

        // 1. Check Login
        if (userId == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        // 2. Lấy dữ liệu
        String type = req.getParameter("type");
        String giverName = req.getParameter("giverName");
        String relationship = req.getParameter("relationship");
        String amountStr = req.getParameter("amount");
        String wish = req.getParameter("wish");

        String error = null;
        BigDecimal amount = BigDecimal.ZERO;

        // 3. Validate (Kiểm tra lỗi)
        if (giverName == null || giverName.trim().isEmpty()){
            error = "Không được bỏ trống người gửi/nhận!";
        }

        try {
            if (amountStr != null && !amountStr.trim().isEmpty()) {
                String cleanAmount = amountStr.replace(",", "").replace(".", "");
                amount = new BigDecimal(cleanAmount);
                if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                    error = "Số tiền phải lớn hơn 0!";
                }
            } else {
                error = "Vui lòng nhập số tiền!";
            }
        } catch (NumberFormatException e) {
            error = "Số tiền không hợp lệ! Vui lòng chỉ nhập số.";
        }

        // 4. [FIX LỖI 1] Nếu CÓ LỖI thì dừng lại ngay, không lưu vào DB
        if (error != null) {
            req.setAttribute("errorMessage", error); // Gửi lỗi sang JSP

            // Giữ lại dữ liệu cũ để người dùng đỡ nhập lại
            req.setAttribute("oldGiverName", giverName);
            req.setAttribute("oldAmount", amountStr);
            req.setAttribute("oldWish", wish);

            req.getRequestDispatcher("/add-transaction.jsp").forward(req, resp);
            return; // Dừng code tại đây
        }

        // 5. Xử lý logic lời chúc mặc định
        if (wish == null || wish.trim().isEmpty()){
            wish = "Chúc mừng năm mới!";
        }

        // 6. Lưu vào DB
        Transaction newTransaction = new Transaction(userId, giverName, relationship, amount, type, wish);
        boolean isSuccess = transactionDao.insertTransaction(newTransaction);

        if (isSuccess) {
            // [FIX LỖI 2] Dùng Session để lưu thông báo (Flash Message)
            // Vì sendRedirect sẽ làm mất request attribute, nên phải dùng session
            session.setAttribute("successMessage", "Đã thêm lì xì thành công! Thêm tiếp nào.");

            // Redirect về chính trang thêm mới (reset form)
            resp.sendRedirect("TransactionServlet");
        } else {
            req.setAttribute("errorMessage", "Lỗi hệ thống! Vui lòng thử lại.");
            req.getRequestDispatcher("/add-transaction.jsp").forward(req, resp);
        }
    }
}