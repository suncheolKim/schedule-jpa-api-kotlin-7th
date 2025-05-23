package net.sckim.schedule.api.domain.user.dto;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String email;
    private String password;
}
