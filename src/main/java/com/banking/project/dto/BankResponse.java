package com.banking.project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Standard bank API response")
public class BankResponse {

    @Schema(example = "00")
    private String responseCode;

    @Schema(example = "Success")
    private String responseMessage;

    @Schema(implementation = AccountInfo.class)
    private AccountInfo accountInfo;
}
