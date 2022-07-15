package com.krizan.user.service;

import com.krizan.user.model.ConfirmationToken;
import com.krizan.user.repository.ConfirmationTokenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public record ConfirmationTokenServiceImpl(ConfirmationTokenRepository confirmationTokenRepository)
        implements ConfirmationTokenService {
    @Override
    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    @Override
    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    @Override
    public int setConfirmedAt(String token) {
        return confirmationTokenRepository().updateConfirmedAt(token, LocalDateTime.now());
    }
}
