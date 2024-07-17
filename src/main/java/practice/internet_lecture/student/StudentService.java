package practice.internet_lecture.student;

import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // 회원 가입
    public String register(StudentRegisterRequestDto requestDto) {

        studentRepository.save(new Student(
                requestDto.email(),
                requestDto.nickname()
        ));

        return "회원 가입이 완료되었습니다.";
    }
}
