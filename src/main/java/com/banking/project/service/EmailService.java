package com.banking.project.service;

import com.banking.project.dto.EmailDetails;

public interface EmailService {
    void sendEmailAlert(EmailDetails emailDetails);

}
