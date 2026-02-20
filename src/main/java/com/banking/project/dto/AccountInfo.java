package com.banking.project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Account information details")
public class AccountInfo {
    @Schema(
            description = "Account holder name",
            example = "Ahmed Mohamed"
    )
    private String accountName;

    @Schema(
            description = "Current account balance",
            example = "1500.75"
    )
    private BigDecimal accountBalance;

    @Schema(
            description = "Unique account number",
            example = "ACC123456789"
    )
    private String accountNumber;
}
