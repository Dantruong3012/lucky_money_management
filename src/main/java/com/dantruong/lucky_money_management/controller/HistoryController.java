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
import java.util.List;

@WebServlet(name = "history", value = "/history-servelet")
public class HistoryController extends HttpServlet {
    private  TransactionDao transactionDao = new TransactionDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Integer userId = (Integer) session.getAttribute("currentUserId") ;

        List<Transaction> list = transactionDao.findAllByUserId(userId);


        req.setAttribute("transactionList", list);

        req.getRequestDispatcher("history.jsp").forward(req, resp);

    }

}
