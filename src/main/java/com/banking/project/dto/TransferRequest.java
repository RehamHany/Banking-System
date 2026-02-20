package com.banking.project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
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
@Schema(description = "Request body for fund transfer between accounts")
public class TransferRequest {

    @NotBlank
    @Schema(example = "ACC100000001")
    private String sourceAccountNumber;

    @NotBlank
    @Schema(example = "ACC200000002")
    private String destinationAccountNumber;

    @NotNull
    @Positive
    @Schema(example = "500.75")
    private BigDecimal amount;
}
