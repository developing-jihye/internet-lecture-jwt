package practice.internet_lecture.student;

import jakarta.validation.constraints.NotNull;

public record LoginRequestDto(
        String email,

        @NotNull
        String password
) {
}
