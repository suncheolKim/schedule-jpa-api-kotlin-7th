package net.sckim.schedule.api.domain.schedule.dto;

import lombok.Getter;
import net.sckim.schedule.api.domain.comment.dto.CommentResponse;
import net.sckim.schedule.api.domain.schedule.entity.Schedule;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ScheduleAndCommentsResponse {
    private Long id;
    private Long userId;
    private String userName;
    private String title;
    private String contents;
    private List<CommentResponse> commentList;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ScheduleAndCommentsResponse of(ScheduleResponse schedule, List<CommentResponse> commentResponseList) {
        final ScheduleAndCommentsResponse response = new ScheduleAndCommentsResponse();

        response.id = schedule.getId();
        response.userId = schedule.getUserId();
        response.userName = schedule.getUserName();
        response.title = schedule.getTitle();
        response.contents = schedule.getContents();
        response.commentList = commentResponseList;
        response.createdAt = schedule.getCreatedAt();
        response.updatedAt = schedule.getUpdatedAt();

        return response;
    }
}
