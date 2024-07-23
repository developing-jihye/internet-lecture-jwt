package practice.internet_lecture.student;

import org.springframework.stereotype.Service;
import practice.internet_lecture.Course.Course;
import practice.internet_lecture.Course.CourseRepository;
import practice.internet_lecture.JwtProvider;

import java.util.NoSuchElementException;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final JwtProvider jwtProvider;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository, EnrollmentRepository enrollmentRepository, JwtProvider jwtProvider) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.jwtProvider = jwtProvider;
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

    // 로그인
    private Student authenticate(LoginRequestDto requestDto) {
        // email 검증
        Student student = studentRepository.findByEmail(requestDto.email())
                .orElseThrow(() -> new IllegalArgumentException("ID 또는 PW가 틀립니다."));

        // password 검증
        if (!student.authenticate(requestDto.password())) {
            throw new IllegalArgumentException("ID 또는 PW가 틀립니다.");
        }

        return student;
    }

    public String generateToken(Student student) {
        // 주입받은 JwtProvider 오브젝트를 통해 토큰 발급
        return jwtProvider.createToken(student.getEmail());
    }

    public LoginResponseDto authenticateAndGenerateToken(LoginRequestDto requestDto) {
        Student student = authenticate(requestDto);
        String token = generateToken(student);
        return new LoginResponseDto(token);
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
