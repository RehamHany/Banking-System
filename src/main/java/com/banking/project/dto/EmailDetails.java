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
@Schema(description = "Email details request body")
public class EmailDetails {

    @Schema(
            description = "Recipient email address",
            example = "user@example.com"
    )
    private String recipient;

    @Schema(
            description = "Email message body content",
            example = "Hello, this is a test email."
    )
    private String messageBody;

    @Schema(
            description = "Email subject",
            example = "Test Email"
    )
    private String subject;

    @Schema(
            description = "File attachment path or name",
            example = "/tmp/report.pdf"
    )
    private String attachment;
}
