package com.krizan.user.dto;

public record UserRegistrationRequest(
        String userName,
        String email,
        String password,
        String repeatPassword) {
}
