package com.krizan.user.controller;

import com.krizan.user.dto.UserRegistrationRequest;
import com.krizan.user.service.RegistrationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registration")
public record RegistrationController(RegistrationService registrationService) {

    @PostMapping
    public String register(@RequestBody UserRegistrationRequest request) {
        return registrationService.registerUser(request);
    }

    @GetMapping
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
