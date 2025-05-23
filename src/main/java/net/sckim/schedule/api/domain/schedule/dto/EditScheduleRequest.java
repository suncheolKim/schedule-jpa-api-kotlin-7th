package net.sckim.schedule.api.domain.schedule.dto;

import jakarta.validation.constraints.AssertTrue;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class EditScheduleRequest {
    private String title;
    private String contents;

    @AssertTrue(message = "최소 하나의 필드는 입력 되어야 합니다.")
    private boolean isAtLeastOneFieldPresent() {
        return StringUtils.isNotBlank(title) || StringUtils.isNotBlank(contents);
    }
}
