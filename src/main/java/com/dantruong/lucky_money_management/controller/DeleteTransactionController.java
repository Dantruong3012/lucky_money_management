package com.dantruong.lucky_money_management.controller;

import com.dantruong.lucky_money_management.model.dao.TransactionDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "delete", value = {"/delete-servelet"})
public class DeleteTransactionController extends HttpServlet {
private TransactionDao transactionDao = new TransactionDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String idStr = req.getParameter("id");
       try {
          if (idStr != null){
              int id = Integer.parseInt(idStr);
              transactionDao.deleteTransactionById(id);
          }
       }catch (NumberFormatException e){e.printStackTrace();}
       resp.sendRedirect("history-servelet");

    }

}
