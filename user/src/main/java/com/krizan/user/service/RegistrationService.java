package com.krizan.user.service;

import com.krizan.user.dto.UserRegistrationRequest;
import org.springframework.transaction.annotation.Transactional;

public interface RegistrationService {

    String registerUser(UserRegistrationRequest request);
    @Transactional
    String confirmToken(String token);
}
