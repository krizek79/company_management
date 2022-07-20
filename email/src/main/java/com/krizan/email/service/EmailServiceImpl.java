package com.krizan.email.service;

import com.krizan.email.dto.EmailRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public Boolean validate(String email) {
        String regex = "^[a-zA-Z\\d_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z\\d.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    @Async
    public void send(EmailRequest request) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
            mimeMessageHelper.setText(request.emailBody(), true);
            mimeMessageHelper.setTo(request.to());
            mimeMessageHelper.setSubject("Confirmation email");
            mimeMessageHelper.setFrom("krizan.matej79@gmail.com");
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.info("Failed to send email.", e);
            throw new IllegalStateException("Failed to send email.");
        }
    }
}
