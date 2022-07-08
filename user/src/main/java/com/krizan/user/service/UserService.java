package com.krizan.user.service;

import com.krizan.user.dto.UserRegistrationRequest;
import com.krizan.user.model.User;
import com.krizan.user.model.VerificationToken;

public interface UserService {
    User registerUser(UserRegistrationRequest request);

    void saveVerificationTokenForUser(String token, User user);

    String validateVerificationToken(String token);

    VerificationToken generateNewVerificationToken(String oldToken);
}
