package practice.internet_lecture.student;

import jakarta.persistence.*;
import practice.internet_lecture.Course.Course;

import java.time.LocalDateTime;

@Entity
public class Enrollment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Course course;

    private LocalDateTime createdDateTime = LocalDateTime.now();

    // constructors
    public Enrollment() {
    }

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
    }
}
