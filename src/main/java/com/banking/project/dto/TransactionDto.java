package com.banking.project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "Data transfer object for a transaction")
public class TransactionDto {

    @Schema(description = "Type of transaction (e.g., DEPOSIT, WITHDRAWAL)", example = "DEPOSIT")
    private String transactionType;

    @Schema(description = "Amount involved in the transaction", example = "1000.50")
    private BigDecimal amount;

    @Schema(description = "Bank account number associated with the transaction", example = "1234567890")
    private String accountNumber;

    @Schema(description = "Status of the transaction (e.g., SUCCESS, FAILED)", example = "SUCCESS")
    private String status;
}

