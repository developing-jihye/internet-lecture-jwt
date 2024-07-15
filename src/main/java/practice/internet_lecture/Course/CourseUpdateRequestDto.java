package practice.internet_lecture.Course;

public record CourseUpdateRequestDto(
        String title,
        String description,
        Integer price
) {
}
