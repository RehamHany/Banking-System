package com.banking.project.controller;

import com.banking.project.dto.*;
import com.banking.project.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "User account management apis")
public class UserController {
    @Autowired
    UserService userService;

    @Operation(
            summary = "Create new user account",
            description = "Creating a new user and assigning an account ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "HTTP Status 201 CREATED")
    })
    @PostMapping
    public BankResponse createAccount(@RequestBody UserRequest userRequest){
        return userService.createAccount(userRequest);
    }

    @Operation(
            summary = "Login user account",
            description = "login user and assigning an account email and password"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "HTTP Status 200 Success")
    })
    @PostMapping("/login")
    public BankResponse login(@RequestBody LoginDto loginDto){
        return userService.login(loginDto);
    }


    @Operation(
            summary = "balanceEnquiry",
            description = "given an account number, check how mush user has"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
    })
    @GetMapping("/balanceEnquiry")
    public BankResponse balanceEnquiry(@RequestBody EnquiryRequest enquiryRequest){
        return userService.balanceEnquiry(enquiryRequest);
    }

    @Operation(
            summary = "nameEnquiry",
            description = "given an account number, return name of User"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
    })
    @GetMapping("/nameEnquiry")
    public String nameEnquiry(@RequestBody EnquiryRequest enquiryRequest){
        return userService.nameEnquiry(enquiryRequest);
    }

    @Operation(
            summary = "creditAccount",
            description = "given an credit request to credit process"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
    })
    @PostMapping("/credit")
    public  BankResponse creditAccount (@RequestBody CreditDebitRequest request){
        return userService.creditAccount(request);
    }

    @Operation(
            summary = "debitAccount",
            description = "given an debit request to debit process"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
    })
    @PostMapping("/debit")
    public  BankResponse debitAccount (@RequestBody CreditDebitRequest request){
        return userService.debitAccount(request);
    }

    @Operation(
            summary = "transfer",
            description = "given an transfer request to transfer process"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
    })
    @PostMapping("/transfer")
    public  BankResponse transfer (@RequestBody TransferRequest request){
        return userService.transfer(request);
    }

}
