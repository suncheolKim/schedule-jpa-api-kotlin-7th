package net.sckim.schedule.api.domain.user.dto;

import lombok.Getter;
import net.sckim.schedule.api.domain.user.entity.User;

import java.io.Serializable;

@Getter
public class LoginResponse implements Serializable {
    private Long id;
    private String name;
    private String email;

    public static LoginResponse of(User user) {
        final LoginResponse response = new LoginResponse();

        response.id = user.getId();
        response.name = user.getName();
        response.email = user.getEmail();

        return response;
    }
}
