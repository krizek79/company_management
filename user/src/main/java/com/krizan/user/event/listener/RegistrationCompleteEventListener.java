package com.krizan.user.event.listener;

import com.krizan.user.event.RegistrationCompleteEvent;
import com.krizan.user.model.User;
import com.krizan.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public record RegistrationCompleteEventListener(UserService userService)
        implements ApplicationListener<RegistrationCompleteEvent> {

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(token, user);
        //  TODO: send email with url
        String url = "http://localhost:9191/api/users/verifyRegistration?token=" + token;
        log.info("Verify user: " + url);
    }
}
