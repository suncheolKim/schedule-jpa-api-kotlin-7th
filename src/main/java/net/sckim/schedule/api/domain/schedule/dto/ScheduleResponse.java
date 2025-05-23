package net.sckim.schedule.api.domain.schedule.dto;

import lombok.Getter;
import net.sckim.schedule.api.domain.schedule.entity.Schedule;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponse {
    private Long id;
    private Long userId;
    private String title;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ScheduleResponse of(Schedule schedule) {
        final ScheduleResponse response = new ScheduleResponse();

        response.id = schedule.getId();
        response.userId = schedule.getUserId();
        response.title = schedule.getTitle();
        response.contents = schedule.getContents();
        response.createdAt = schedule.getCreatedAt();
        response.updatedAt = schedule.getUpdatedAt();

        return response;
    }
}
