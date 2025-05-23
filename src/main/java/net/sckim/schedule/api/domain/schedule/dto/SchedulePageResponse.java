package net.sckim.schedule.api.domain.schedule.dto;

import lombok.Getter;
import net.sckim.schedule.api.domain.schedule.entity.Schedule;

import java.time.LocalDateTime;

@Getter
public class SchedulePageResponse {
    private Long id;
    private String title;
    private String contents;
    private Long userName;
    private Long commentCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public static SchedulePageResponse of(Schedule schedule) {
        final SchedulePageResponse dto = new SchedulePageResponse();

        dto.id = schedule.getId();
        dto.title = schedule.getTitle();
        dto.contents = schedule.getContents();
        dto.userName = schedule.getUserId();
        dto.commentCount = 0L;
        dto.createdAt = schedule.getCreatedAt();
        dto.updatedAt = schedule.getUpdatedAt();

        return dto;
    }
}
