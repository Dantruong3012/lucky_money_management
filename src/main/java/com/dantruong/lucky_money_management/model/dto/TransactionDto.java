package com.dantruong.lucky_money_management.model.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TransactionDto {
   private Timestamp date;
   private  BigDecimal income;
   private  BigDecimal expense;

    public TransactionDto() {
        this.income = BigDecimal.ZERO;
        this.expense = BigDecimal.ZERO;
    }

    public TransactionDto(Timestamp date, BigDecimal income, BigDecimal expense) {
        this.date = date;
        this.income = income;
        this.expense = expense;
    }


    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public BigDecimal getExpense() {
        return expense;
    }

    public void setExpense(BigDecimal expense) {
        this.expense = expense;
    }
}
