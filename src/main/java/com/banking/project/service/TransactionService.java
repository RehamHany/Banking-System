package com.banking.project.service;

import com.banking.project.dto.TransactionDto;
import com.banking.project.entity.Transaction;

public interface TransactionService {
    void saveTransaction(TransactionDto transaction);
}
