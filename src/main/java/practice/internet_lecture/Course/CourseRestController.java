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
    public CourseCreateDetailResponseDto createOne(@RequestBody CourseCreateRequestDto requestDto) {
        return courseService.createOne(requestDto);
    }

    // 강의 목록 조회
    @GetMapping
    public List<CourseCreateResponseDto> findAll() {
        return courseService.findAll();
    }

    // 강의 상세 조회
    @GetMapping("/{courseId}")
    public CourseCreateDetailResponseDto findById(@PathVariable("courseId") Long courseId) {
        return courseService.findById(courseId);
    }

    // 강의 수정
    @PutMapping("/{courseId}")
    public void update(@PathVariable Long couseId, @RequestBody CourseUpdateRequestDto body) {
        courseService.updateById(couseId, body);
    }

}
