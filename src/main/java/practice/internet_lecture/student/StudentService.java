package practice.internet_lecture.student;

import org.springframework.stereotype.Service;
import practice.internet_lecture.Course.Course;
import practice.internet_lecture.Course.CourseRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository, EnrollmentRepository enrollmentRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    // 회원 가입
    public String register(StudentRegisterRequestDto requestDto) {

        studentRepository.save(new Student(
                requestDto.email(),
                requestDto.nickname(),
                SecurityUtils.sha256Encrypt(requestDto.password())
        ));

        return "회원 가입이 완료되었습니다.";
    }

    // 수강 신청
    public String enroll(EnrollmentRequestDto requestDto) {

        Student student = studentRepository.findById(requestDto.studentId())
                        .orElse(null);

        Course course = courseRepository.findById(requestDto.courseId())
                        .orElse(null);

        if(student == null || course == null) {
            new IllegalArgumentException("강의 또는 회원 ID가 없습니다.");
        }

        enrollmentRepository.save(new Enrollment(student, course));

        return "수강 신청이 완료되었습니다.";
    }

}
