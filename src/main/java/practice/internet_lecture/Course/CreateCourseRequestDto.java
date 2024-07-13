package practice.internet_lecture.Course;

public record CreateCourseRequestDto(
        String title,
        Long instructorId,
        String description,
        Integer price,
        CourseCategory category
) {
}
