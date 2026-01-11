package com.dantruong.lucky_money_management.mapper;

import com.dantruong.lucky_money_management.model.dto.TransactionDto;
import com.dantruong.lucky_money_management.model.entity.Transaction;

import java.math.BigDecimal;

public class TransactionMapper {
    public  static TransactionDto transactionEntityToDto(Transaction transaction){
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setDate(transaction.getCreatedTime());
        String type = transaction.getType();
        if ("INCOME".equalsIgnoreCase(type)){
            transactionDto.setIncome(transaction.getAmount());
            transactionDto.setExpense(BigDecimal.ZERO);
        }else if ("EXPENSE".equalsIgnoreCase(type)){
            transactionDto.setExpense(transaction.getAmount());
            transactionDto.setIncome(BigDecimal.ZERO);
        }else {
            transactionDto.setIncome(BigDecimal.ZERO);
            transactionDto.setExpense(BigDecimal.ZERO);
        }

        return  transactionDto;
    }
}
