package net.sckim.schedule.api.domain.user.dto;

import lombok.Getter;

@Getter
public class CreateUserRequest {
    private String name;
    private String email;
}
