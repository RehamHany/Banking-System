package com.banking.project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Request body for credit or debit transaction")
public class CreditDebitRequest {

    @NotNull
    @Schema(example = "ACC123456789")
    private String accountNumber;

    @NotNull
    @Positive
    @Schema(example = "250.50")
    private BigDecimal amount;
}
