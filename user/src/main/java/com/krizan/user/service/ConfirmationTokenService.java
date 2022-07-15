package com.krizan.user.service;

import com.krizan.user.model.ConfirmationToken;

import java.util.Optional;

public interface ConfirmationTokenService {

    void saveConfirmationToken(ConfirmationToken token);
    Optional<ConfirmationToken> getToken(String token);
    int setConfirmedAt(String token);
}
