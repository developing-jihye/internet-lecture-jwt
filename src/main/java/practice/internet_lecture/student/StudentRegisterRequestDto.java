package practice.internet_lecture.student;

public record StudentRegisterRequestDto(
        String email,
        String nickname,
        String password
) {
}
