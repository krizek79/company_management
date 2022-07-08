package com.krizan.user.event;

import com.krizan.user.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {

    private final User user;

    public RegistrationCompleteEvent(User user) {
        super(user);
        this.user = user;
    }
}
