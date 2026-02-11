package com.banking.project.service;

import com.banking.project.dto.*;
import com.banking.project.entity.User;
import com.banking.project.repo.UserRepo;
import com.banking.project.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    EmailService emailService;

    @Override
    public BankResponse createAccount(UserRequest userRequest) {
        if(userRepo.existsByEmail(userRequest.getEmail())){
             return BankResponse.builder()
                       .responseCode(AccountUtils.ACCOUNT_EXISTS_CODE)
                       .responseMessage(AccountUtils.ACCOUNT_EXISTS_MESSAGE)
                       .accountInfo(null)
                       .build();
        }
        User newUser = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .otherName(userRequest.getOtherName())
                .gender(userRequest.getGender())
                .address(userRequest.getAddress())
                .stateOfOrigin(userRequest.getStateOfOrigin())
                .accountNumber(AccountUtils.generateAccountNumber())
                .email(userRequest.getEmail())
                .accountBalance(BigDecimal.ZERO)
                .phoneNumber(userRequest.getPhoneNumber())
                .alternativePhoneNumber(userRequest.getAlternativePhoneNumber())
                .status("ACTIVE")
                .build();

        User savedUser = userRepo.save(newUser);

        // send email alert!
        EmailDetails emailDetails =EmailDetails.builder()
                .recipient(savedUser.getEmail())
                .subject("ACCOUNT CREATION")
                .messageBody("Congratulations! Your Account Has been Successfully Created :) \n Your Account Details: \n" +
                        "Account Name: " + savedUser.getFirstName() + " " + savedUser.getLastName() + " " +savedUser.getOtherName() + "\nAccount Number: " + savedUser.getAccountNumber())
                .build();
        emailService.sendEmailAlert(emailDetails);

        return BankResponse.builder()
               .responseCode(AccountUtils.ACCOUNT_CREATION_SUCCESS)
               .responseMessage(AccountUtils.ACCOUNT_CREATION_MESSAGE)
               .accountInfo(AccountInfo.builder()
                       .accountBalance(savedUser.getAccountBalance())
                       .accountName(savedUser.getAccountNumber())
                       .accountName(savedUser.getFirstName() + " " + savedUser.getLastName() + " " + savedUser.getOtherName() )
                       .build())
               .build();
    }

    @Override
    public BankResponse balanceEnquiry(EnquiryRequest enquiryRequest) {
         boolean isAccountExist = userRepo.existsByAccountNumber(enquiryRequest.getAccountNumber());
         if(!isAccountExist){
           return BankResponse.builder()
                   .responseCode(AccountUtils.ACCOUNT_NOT_EXIST_CODE)
                   .responseMessage(AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE)
                   .accountInfo(null)
                   .build();
         }

         User foundUser = userRepo.findByAccountNumber(enquiryRequest.getAccountNumber());
return BankResponse.builder()
        .responseCode(AccountUtils.ACCOUNT_FOUND_CODE)
        .responseMessage(AccountUtils.ACCOUNT_FOUND_SUCCESS)
        .accountInfo(AccountInfo.builder()
                .accountBalance(foundUser.getAccountBalance())
                .accountNumber(foundUser.getAccountNumber())
                .accountName(foundUser.getFirstName() + " " + foundUser.getLastName() + " " +foundUser.getOtherName())
                .build())
        .build();
    }

    @Override
    public String nameEnquiry(EnquiryRequest enquiryRequest) {
        boolean isAccountExist = userRepo.existsByAccountNumber(enquiryRequest.getAccountNumber());
        if(!isAccountExist){
               return AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE;
        }
        User foundUser = userRepo.findByAccountNumber(enquiryRequest.getAccountNumber());
        return foundUser.getFirstName() + " " + foundUser.getLastName() + " " +foundUser.getOtherName();
    }

    @Override
    public BankResponse creditAccount(CreditDebitRequest request) {
        boolean isAccountExist = userRepo.existsByAccountNumber(request.getAccountNumber());
        if(!isAccountExist){
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_NOT_EXIST_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();
        }
        User userToCredit = userRepo.findByAccountNumber(request.getAccountNumber());
        userToCredit.setAccountBalance(userToCredit.getAccountBalance().add(request.getAmount()));
        userRepo.save(userToCredit);


        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREDITED_SUCCESS)
                .responseMessage(AccountUtils.ACCOUNT_CREDITED_SUCCESS_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountBalance(userToCredit.getAccountBalance())
                        .accountNumber(userToCredit.getAccountNumber())
                        .accountName(userToCredit.getFirstName() + " " + userToCredit.getLastName() + " " + userToCredit.getOtherName())
                        .build())
                .build();
    }

    @Override
    public BankResponse debitAccount(CreditDebitRequest request) {
        boolean isAccountExist = userRepo.existsByAccountNumber(request.getAccountNumber());
        if(!isAccountExist){
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_NOT_EXIST_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();
        }

        User userToDebit = userRepo.findByAccountNumber(request.getAccountNumber());
        BigInteger availableBalance = userToDebit.getAccountBalance().toBigInteger();
        BigInteger debitAmount = request.getAmount().toBigInteger();
        if(availableBalance.intValue() < debitAmount.intValue()){
            return BankResponse.builder()
                    .responseCode(AccountUtils.INSUFFICIENT_BALANCE_CODE)
                    .responseMessage(AccountUtils.INSUFFICIENT_BALANCE_MESSAGE)
                    .accountInfo(null)
                    .build();
        }
        else {
            userToDebit.setAccountBalance(userToDebit.getAccountBalance().subtract(request.getAmount()));
            userRepo.save(userToDebit);
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_DEBITED_SUCCESS)
                    .responseMessage(AccountUtils.ACCOUNT_DEBITED_MESSAGE)
                    .accountInfo(AccountInfo.builder()
                            .accountNumber(request.getAccountNumber())
                            .accountName(userToDebit.getFirstName() + " " + userToDebit.getLastName() + " " + userToDebit.getOtherName())
                            .accountBalance(userToDebit.getAccountBalance())
                            .build())
                    .build();
        }
    }

    @Override
    public BankResponse transfer(TransferRequest request) {
           boolean isDestinationAccountExist = userRepo.existsByAccountNumber(request.getDestinationAccountNumber());
           if(!isDestinationAccountExist){
               return BankResponse.builder()
                       .responseCode(AccountUtils.ACCOUNT_NOT_EXIST_CODE)
                       .responseMessage(AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE)
                       .accountInfo(null)
                       .build();
           }

           User sourceAccountUser = userRepo.findByAccountNumber(request.getSourceAccountNumber());
           if(request.getAmount().compareTo(sourceAccountUser.getAccountBalance())>0){
              return BankResponse.builder()
                      .responseCode(AccountUtils.INSUFFICIENT_BALANCE_CODE)
                      .responseMessage(AccountUtils.INSUFFICIENT_BALANCE_MESSAGE)
                      .accountInfo(null)
                      .build();
           }

           sourceAccountUser.setAccountBalance(sourceAccountUser.getAccountBalance().subtract(request.getAmount()));
           String sourceAccountName = sourceAccountUser.getFirstName() + " " + sourceAccountUser.getLastName() + " " + sourceAccountUser.getOtherName();
           userRepo.save(sourceAccountUser);
           EmailDetails debitAlert = EmailDetails.builder()
                   .subject("DEBIT ALERT")
                   .recipient(sourceAccountUser.getEmail())
                   .messageBody("the some of " + request.getAmount() + "has been deducted from your account! Your current balance is " + sourceAccountUser.getAccountBalance())
                   .build();

           emailService.sendEmailAlert(debitAlert);


           User destinationAccountUser = userRepo.findByAccountNumber(request.getDestinationAccountNumber());
           destinationAccountUser.setAccountBalance(destinationAccountUser.getAccountBalance().add(request.getAmount()));
           // String recipientUserName = destinationAccountUser.getFirstName() + " " + destinationAccountUser.getLastName() + " " + destinationAccountUser.getOtherName();
           userRepo.save(destinationAccountUser);

        EmailDetails creditAlert = EmailDetails.builder()
                .subject("CREDIT ALERT")
                .recipient(sourceAccountUser.getEmail())
                .messageBody("the some of " + request.getAmount() + "has been sent to your account from ! " + sourceAccountName+ " Your current balance is " + sourceAccountUser.getAccountBalance())
                .build();

        emailService.sendEmailAlert(debitAlert);

        return BankResponse.builder()
                .responseCode(AccountUtils.TRANSFER_SUCCESSFULLY_CODE)
                .responseMessage(AccountUtils.TRANSFER_SUCCESSFULLY_MESSAGE)
                .accountInfo(null)
                .build();

    }


}
