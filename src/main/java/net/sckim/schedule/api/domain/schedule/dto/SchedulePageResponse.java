package net.sckim.schedule.api.domain.schedule.dto;

import lombok.Getter;
import net.sckim.schedule.api.domain.schedule.entity.Schedule;

import java.time.LocalDateTime;

@Getter
public class SchedulePageResponse {
    private Long id;
    private String title;
    private String contents;
    private String userName;
    private Long commentCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static SchedulePageResponse of(Schedule schedule, Long commentCount) {
        final SchedulePageResponse dto = new SchedulePageResponse();

        dto.id = schedule.getId();
        dto.title = schedule.getTitle();
        dto.contents = schedule.getContents();
        dto.userName = schedule.getUser().getName();
        dto.commentCount = commentCount;
        dto.createdAt = schedule.getCreatedAt();
        dto.updatedAt = schedule.getUpdatedAt();

        return dto;
    }
}
