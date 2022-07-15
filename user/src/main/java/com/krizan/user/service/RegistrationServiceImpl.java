package com.krizan.user.service;

import com.krizan.user.dto.UserRegistrationRequest;
import com.krizan.user.exception.IllegalOperationException;
import com.krizan.user.exception.NotFoundException;
import com.krizan.user.model.ConfirmationToken;
import com.krizan.user.model.User;
import com.krizan.user.vo.UserRole;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record RegistrationServiceImpl(UserService userService,
                                      ConfirmationTokenService confirmationTokenService) implements RegistrationService{
    @Override
    public String registerUser(UserRegistrationRequest request) {
        //  TODO: validate email

        if (!request.password().equals(request.repeatPassword()))
            throw new IllegalOperationException("Passwords do not match.");

        User user = User.builder()
                .userName(request.userName())
                .email(request.email())
                .password(request.password())
                .userRole(UserRole.USER)
                .build();

        String token = userService.signUpUser(user);

        //  TODO: send email

        return token;
    }

    @Override
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token).orElseThrow(NotFoundException::new);
        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }
        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }
        confirmationTokenService.setConfirmedAt(token);
        userService.enableUser(confirmationToken.getUser().getEmail());
        return "confirmed";
    }
}
