package practice.internet_lecture.student;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentRestConstroller {

    private final StudentService studentService;

    public StudentRestConstroller(StudentService studentService) {
        this.studentService = studentService;
    }

    // 회원 가입
    @PostMapping("/students")
    public String create(@RequestBody StudentRegisterRequestDto requestDto) {
        return studentService.register(requestDto);
    }

    // 로그인
    @PostMapping("/login")
    public LoginResponseDto login(@Valid @RequestBody LoginRequestDto requestDto) {
        return studentService.authenticateAndGenerateToken(requestDto);
    }

    // 수강 신청
    @PostMapping("/enrollments")
    public String enroll(@RequestBody EnrollmentRequestDto requestDto) {
        return studentService.enroll(requestDto);
    }
}
