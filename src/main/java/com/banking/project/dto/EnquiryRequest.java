package com.banking.project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Request body for account enquiry")
public class EnquiryRequest {

    @Schema(
            description = "Bank account number for enquiry",
            example = "ACC123456789"
    )
    private String accountNumber;
}
