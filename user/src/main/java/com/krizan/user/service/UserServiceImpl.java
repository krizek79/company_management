package com.krizan.user.service;

import com.krizan.user.exception.IllegalOperationException;
import com.krizan.user.exception.NotFoundException;
import com.krizan.user.model.ConfirmationToken;
import com.krizan.user.model.User;
import com.krizan.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public record UserServiceImpl(
        UserRepository userRepository,
        BCryptPasswordEncoder bCryptPasswordEncoder,
        ConfirmationTokenService confirmationTokenService) implements UserService {

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(NotFoundException::new);
    }

    public String signUpUser(User user) {
        boolean emailTaken = userRepository.findByEmail(user.getEmail()).isPresent();
        boolean nicknameTaken = userRepository.findByUserName(user.getUsername()).isPresent();
        if (emailTaken || nicknameTaken) throw new IllegalOperationException("Username or email is already taken.");

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);

        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        //  TODO: send email

        return confirmationToken.getToken();
    }

    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }
}
