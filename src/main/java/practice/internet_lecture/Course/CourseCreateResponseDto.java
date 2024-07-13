package practice.internet_lecture.Course;

import java.time.LocalDateTime;

public record CourseCreateResponseDto(
        Long courseId,
        String title,
        String instructorName,
        Integer price,
        Integer numOfStudents,
        CourseCategory category,
        LocalDateTime createdAt
) {
}
