package practice.internet_lecture.Course;

public record CourseCreateRequestDto(
        String title,
        Long instructorId,
        String description,
        Integer price,
        CourseCategory category
) {
}
