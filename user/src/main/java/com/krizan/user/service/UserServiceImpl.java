package com.krizan.user.service;

import com.krizan.user.dto.UserRegistrationRequest;
import com.krizan.user.model.User;
import com.krizan.user.model.VerificationToken;
import com.krizan.user.repository.UserRepository;
import com.krizan.user.repository.VerificationTokenRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.UUID;

@Service
public record UserServiceImpl(
        UserRepository userRepository,
        VerificationTokenRepository verificationTokenRepository,
        PasswordEncoder passwordEncoder
) implements UserService {
    @Override
    public User registerUser(UserRegistrationRequest request) {
        User user = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .role("USER")
                .password(passwordEncoder().encode(request.password()))
                .build();
        return userRepository.save(user);
    }

    @Override
    public void saveVerificationTokenForUser(String token, User user) {
        VerificationToken verificationToken = new VerificationToken(user, token);
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public String validateVerificationToken(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if (verificationToken == null) {
            //  TODO: throw exception
            return "invalid";
        }
        User user = verificationToken.getUser();
        Calendar calendar = Calendar.getInstance();
        if ((verificationToken.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0) {
            verificationTokenRepository.delete(verificationToken);
            //  TODO: throw exception
            return "expired";
        }
        user.setEnabled(true);
        userRepository.save(user);
        return "valid";
    }

    @Override
    public VerificationToken generateNewVerificationToken(String oldToken) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(oldToken);
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationTokenRepository.save(verificationToken);
        return verificationToken;
    }
}
