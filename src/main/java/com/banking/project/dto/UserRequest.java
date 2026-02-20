package com.banking.project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Request body for creating a new bank user")
public class UserRequest {

    @Schema(description = "User first name", example = "Ahmed")
    private String firstName;

    @Schema(description = "User last name", example = "Hassan")
    private String lastName;

    @Schema(description = "User other/middle name", example = "Ali")
    private String otherName;

    @Schema(description = "User gender", example = "MALE")
    private String gender;

    @Schema(description = "User residential address", example = "Cairo, Nasr City")
    private String address;

    @Schema(description = "User state of origin", example = "Cairo")
    private String stateOfOrigin;

    @Schema(description = "User bank account number", example = "ACC123456789")
    private String accountNumber;

    @Schema(description = "User primary phone number", example = "+201001234567")
    private String phoneNumber;

    @Schema(description = "User alternative phone number", example = "+201112345678")
    private String alternativePhoneNumber;

    @Email
    @Schema(description = "User email address", example = "ahmed.hassan@example.com")
    private String email;

    @Schema(description = "User password ", example = "ahmed1230")
    private String password;
}
