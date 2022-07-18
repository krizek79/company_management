package com.krizan.email.controller;

import com.krizan.email.dto.EmailRequest;
import com.krizan.email.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public record EmailController(EmailService emailService) {

    @PostMapping("/validate")
    public Boolean validateEmail(@RequestBody String email) {
        return emailService.validate(email);
    }

    @PostMapping("/send")
    public void sendEmail(@RequestBody EmailRequest request) {
        emailService.send(request);
    }
}
