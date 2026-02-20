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
@Schema(description = "Login information details")
public class LoginDto {
    @Schema(description = "User email address", example = "ahmed.hassan@example.com")
    private String email;
    @Schema(description = "User password ", example = "ahmed1230")
    private String password;
}
