package practice.internet_lecture.Course;

import jakarta.persistence.*;
import practice.internet_lecture.instructor.Instructor;

import java.time.LocalDateTime;

@Entity
public class Course {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    @ManyToOne
    private Instructor instructor;

    private String description;

    private Integer price;

    @Enumerated(EnumType.STRING)
    private CourseCategory category;

    private LocalDateTime createdDateTime = LocalDateTime.now();

    private LocalDateTime modifiedDateTime = LocalDateTime.now();

    // constructors
    public Course() {
    }

    public Course(String title, Instructor instructor, String description, Integer price, CourseCategory category) {
        this.title = title;
        this.instructor = instructor;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    // getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPrice() {
        return price;
    }

    public CourseCategory getCategory() {
        return category;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public LocalDateTime getModifiedDateTime() {
        return modifiedDateTime;
    }

    // 메서드
    public void updateTitleDescriptionPrice(
            String title,
            String description,
            Integer price
    ) {
        this.title = title;
        this.description = description;
        this.price = price;
    }
}
