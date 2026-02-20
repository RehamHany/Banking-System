package com.banking.project.service;

import com.banking.project.dto.TransactionDto;
import com.banking.project.entity.Transaction;
import com.banking.project.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImp implements TransactionService{
    @Autowired
    TransactionRepo transactionRepo;

    @Override
    public void saveTransaction(TransactionDto transactionDto) {
        Transaction transaction = Transaction.builder()
                .transactionType(transactionDto.getTransactionType())
                .accountNumber(transactionDto.getAccountNumber())
                .amount(transactionDto.getAmount())
                .status("SUCCESS:)")
                .build();

        transactionRepo.save(transaction);
        System.out.println("transaction saved successfully:)");
    }
}
