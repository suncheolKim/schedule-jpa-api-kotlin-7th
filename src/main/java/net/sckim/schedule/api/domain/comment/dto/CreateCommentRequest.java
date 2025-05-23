package net.sckim.schedule.api.domain.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateCommentRequest {
    @Positive
    private Long scheduleId;

    @Positive
    private Long userId;

    @Size(min = 1, max = 500)
    @NotBlank
    private String contents;
}
