package com.krizan.user.service;

import com.krizan.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public record UserServiceImpl(UserRepository userRepository) implements UserService {
}
