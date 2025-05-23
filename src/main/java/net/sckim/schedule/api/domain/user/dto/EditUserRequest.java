package net.sckim.schedule.api.domain.user.dto;

import lombok.Getter;

@Getter
public class EditUserRequest {
    private String name;
    private String email;
}
