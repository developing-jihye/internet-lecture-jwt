package practice.internet_lecture.Course;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseRestController {

    private final CourseService courseService;

    public CourseRestController(CourseService courseService) {
        this.courseService = courseService;
    }

    // 강의 하나 등록
    @PostMapping
    public CourseCreateDetailResponseDto createOne(@RequestBody CreateCourseRequestDto requestDto) {
        return courseService.createOne(requestDto);
    }

    // 강의 목록 조회
    @GetMapping
    public List<CourseCreateResponseDto> findAll() {
        return courseService.findAll();
    }

    // 강의 상세 조회
    @GetMapping("/{courseId}")
    public CourseCreateDetailResponseDto findById(@PathVariable Long courseId) {
        return courseService.findById(courseId);
    }

}
