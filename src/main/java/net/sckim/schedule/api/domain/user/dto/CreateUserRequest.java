package net.sckim.schedule.api.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import net.sckim.schedule.api.domain.common.RegexPattern;

@Getter
public class CreateUserRequest {
    @Size(min = 1, max = 4)
    @NotBlank
    private String name;

    @Pattern(regexp = RegexPattern.EMAIL, message = "올바른 형식의 이메일 주소여야 합니다.")
    private String email;

    @NotBlank
    private String password;
}
