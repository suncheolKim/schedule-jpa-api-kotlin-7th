package net.sckim.schedule.api.domain.user.dto;

import lombok.Getter;
import net.sckim.schedule.api.domain.schedule.entity.Schedule;
import net.sckim.schedule.api.domain.user.entity.User;

import java.time.LocalDateTime;

@Getter
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static UserResponse of(User user) {
        final UserResponse response = new UserResponse();

        response.id = user.getId();
        response.name = user.getName();
        response.email = user.getEmail();
        response.createdAt = user.getCreatedAt();
        response.updatedAt = user.getUpdatedAt();

        return response;
    }
}
