package net.sckim.schedule.api.domain.user.dto;

import jakarta.validation.constraints.AssertTrue;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class EditUserRequest {
    private String name;
    private String email;

    @AssertTrue(message = "최소 하나의 필드는 입력 되어야 합니다.")
    private boolean isAtLeastOneFieldPresent() {
        return StringUtils.isNotBlank(name) || StringUtils.isNotBlank(email);
    }
}
