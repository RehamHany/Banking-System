package com.banking.project.controller;

import com.banking.project.entity.Transaction;
import com.banking.project.service.BankStatement;
import com.itextpdf.text.DocumentException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/bankStatement")
@AllArgsConstructor
@Tag(name = "Transaction management apis")
public class TransactionController {
    @Autowired
    private BankStatement bankStatement;

    @Operation(
            summary = "get statement",
            description = "generate statement from transactions"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
    })
    @GetMapping()
    public List<Transaction> generateStatement(
            @RequestParam String accountNumber,
            @RequestParam String startDate,
            @RequestParam String endDate
    ) throws DocumentException, FileNotFoundException {
        return bankStatement.generateStatement(accountNumber, startDate, endDate);
    }
}
