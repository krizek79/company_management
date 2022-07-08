package com.krizan.user.dto;

public record UserRegistrationRequest(
        String firstName,
        String lastName,
        String email,
        String password) {
}
