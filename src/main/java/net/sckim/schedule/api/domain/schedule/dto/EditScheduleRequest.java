package net.sckim.schedule.api.domain.schedule.dto;

import lombok.Getter;

@Getter
public class EditScheduleRequest {
    private String name;
    private String title;
    private String contents;
}
