package com.krizan.email.service;

import com.krizan.email.dto.EmailRequest;

public interface EmailService {

    Boolean validate(String email);
    void send(EmailRequest request);
}
