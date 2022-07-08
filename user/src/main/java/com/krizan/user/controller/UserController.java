package com.krizan.user.controller;

import com.krizan.user.dto.UserRegistrationRequest;
import com.krizan.user.event.RegistrationCompleteEvent;
import com.krizan.user.model.VerificationToken;
import com.krizan.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Slf4j
public record UserController(UserService userService, ApplicationEventPublisher eventPublisher) {

    @PostMapping("/register")
    public String registerUser(@RequestBody UserRegistrationRequest request) {
        var user = userService.registerUser(request);
        eventPublisher.publishEvent(new RegistrationCompleteEvent(user));
        return "Success";
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token) {
        String result = userService.validateVerificationToken(token);
        if (result.equalsIgnoreCase("valid")) {
            return "User verified successfully";
        }
        return "Bad user";
    }

    @GetMapping("/resendVerificationToken")
    public String resendVerificationToken(@RequestParam("token") String oldToken) {
        VerificationToken verificationToken = userService.generateNewVerificationToken(oldToken);
        resendVerificationTokenMail(verificationToken);
        return "Verification link sent";
    }

    private void resendVerificationTokenMail(VerificationToken verificationToken) {
        String url = "http://localhost:9191/api/users/verifyRegistration?token=" + verificationToken.getToken();
        log.info("Verify user: " + url);
    }
}
