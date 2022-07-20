package com.krizan.user.controller;

import com.krizan.user.dto.UserResponse;
import com.krizan.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public record UserController(UserService userService) {

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(new UserResponse(userService.getUserById(id)), HttpStatus.OK);
    }
}
