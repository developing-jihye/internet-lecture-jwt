package practice.internet_lecture.Course;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
public class CourseRestController {

    private final CourseService courseService;

    public CourseRestController(CourseService courseService) {
        this.courseService = courseService;
    }

    // 강의 하나 등록
    @GetMapping
    public CourseCreateDetailResponseDto createOne(@RequestBody CreateCourseRequestDto requestDto) {
        return courseService.createOne(requestDto);
    }

}
