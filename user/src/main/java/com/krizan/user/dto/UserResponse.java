package com.krizan.user.dto;

import com.krizan.user.model.User;
import lombok.Getter;

@Getter
public class UserResponse {

    private final Long id;
    private final String getUserName;
    private final String email;

    public UserResponse(User user) {
        this.id = user.getId();
        this.getUserName = user.getUsername();
        this.email = user.getEmail();
    }
}
