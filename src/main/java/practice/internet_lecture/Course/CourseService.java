package practice.internet_lecture.Course;

import org.springframework.stereotype.Service;
import practice.internet_lecture.instructor.Instructor;
import practice.internet_lecture.instructor.InstructorRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;

    public CourseService(CourseRepository courseRepository, InstructorRepository instructorRepository) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
    }

    // 강의 하나 등록
    public CourseCreateDetailResponseDto createOne(CreateCourseRequestDto requestDto) {

        // client가 강의 id 안 보낼 경우
        Long instructorId = requestDto.instructorId();
        if (instructorId == null) {
            throw new IllegalArgumentException("입력하신 강사 ID가 null입니다.");
        }

        // client가 보낸 강의 id가 DB에 없는 경우
        Instructor instructor = instructorRepository.findById(requestDto.instructorId())
                .orElseThrow(() -> new IllegalArgumentException("해당 강사가 없습니다."));

        Course newCourse = new Course(
                requestDto.title(),
                instructor,
                requestDto.description(),
                requestDto.price(),
                requestDto.category()
        );
        courseRepository.save(newCourse);
        return new CourseCreateDetailResponseDto(
                newCourse.getTitle(),
                newCourse.getDescription(),
                newCourse.getPrice(),
                new ArrayList<>(),
                newCourse.getCategory(),
                newCourse.getCreatedDateTime(),
                newCourse.getModifiedDateTime()
        );
    }

    // 강의 목록 조회
    public List<CourseCreateResponseDto> findAll() {

        return courseRepository.findAll()
                .stream()
                .map(course -> new CourseCreateResponseDto(
                        course.getId(),
                        course.getTitle(),
                        course.getInstructor().getName(),
                        course.getPrice(),
                        0,
                        course.getCategory(),
                        course.getCreatedDateTime()
                )).toList();
    }

    // 강의 상세 조회
    public CourseCreateDetailResponseDto findById(Long courseId) {

        Course selectedCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("해당 강의가 없습니다."));

        return new CourseCreateDetailResponseDto(
                selectedCourse.getTitle(),
                selectedCourse.getDescription(),
                selectedCourse.getPrice(),
                new ArrayList<>(),
                selectedCourse.getCategory(),
                selectedCourse.getCreatedDateTime(),
                selectedCourse.getModifiedDateTime()
        );
    }
}
