package net.sckim.schedule.api.domain.comment.dto;

import lombok.Getter;
import net.sckim.schedule.api.domain.comment.entity.Comment;

import java.time.LocalDateTime;


@Getter
public class CommentResponse {
    private Long id;
    private Long scheduleId;
    private Long userId;
    private String userName;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static CommentResponse of(Comment comment, Long scheduleId, Long userId, String userName) {
        final CommentResponse response = new CommentResponse();

        response.id = comment.getId();
        response.scheduleId = scheduleId;
        response.userId = userId;
        response.userName = userName;
        response.contents = comment.getContents();
        response.createdAt = comment.getCreatedAt();
        response.updatedAt = comment.getUpdatedAt();

        return response;
    }
}
