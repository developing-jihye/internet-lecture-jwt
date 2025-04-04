package practice.internet_lecture.student;

import jakarta.persistence.*;

@Entity
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String nickname;

    @Column(nullable = false)
    private String password;

    // constructors
    public Student() {
    }

    public Student(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    // getters
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    // 비밀번호 검증 함수
    public boolean authenticate(String rawPassword) {
        String hashedInputPassword = SecurityUtils.sha256Encrypt(rawPassword);
        return this.password.equals(hashedInputPassword);
    }
}
