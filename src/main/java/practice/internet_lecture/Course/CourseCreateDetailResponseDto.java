package practice.internet_lecture.Course;

import java.time.LocalDateTime;
import java.util.List;

public record CourseCreateDetailResponseDto(
        String title,
        String description,
        Integer price,
        List<StudentDto> students,
        CourseCategory category,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public record StudentDto(
            String nickname,
            LocalDateTime enrolledDateTime
    ) {}
}
