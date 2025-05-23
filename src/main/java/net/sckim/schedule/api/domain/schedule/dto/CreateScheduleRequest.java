package net.sckim.schedule.api.domain.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateScheduleRequest {
    @Positive
    private Long userId;

    @Size(min = 1, max = 10)
    @NotBlank
    private String title;

    @Size(min = 1, max = 500)
    @NotBlank
    private String contents;
}
