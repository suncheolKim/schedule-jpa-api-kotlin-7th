package net.sckim.schedule.api.domain.comment.dto;

import lombok.Getter;
import net.sckim.schedule.api.domain.comment.entity.Comment;
import net.sckim.schedule.api.domain.schedule.dto.ScheduleResponse;
import net.sckim.schedule.api.domain.user.dto.UserResponse;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {
    private Long id;
    private ScheduleResponse schedule;
    private UserResponse user;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static CommentResponse of(Comment comment) {
        final CommentResponse response = new CommentResponse();

        response.id = comment.getId();
        response.schedule = ScheduleResponse.of(comment.getSchedule());
        response.user = UserResponse.of(comment.getUser());
        response.contents = comment.getContents();
        response.createdAt = comment.getCreatedAt();
        response.updatedAt = comment.getUpdatedAt();

        return response;
    }
}
