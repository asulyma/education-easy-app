package com.global.shop.model.user;

import com.global.shop.model.learning.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Pojo class.
 *
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String login;

    @Size(min = 2, max = 32)
    private String givenName;

    @Size(min = 2, max = 32)
    private String familyName;

    @Min(value = 12)
    @Max(value = 80)
    private Integer age;

    @Email
    @Column(unique = true)
    private String email;

    @Column(name = "is_locked")
    private boolean isLocked;

    @Column(name = "is_active")
    private boolean isActive;

    @Enumerated(value = EnumType.STRING)
    private Rank rank;

    private String roles;

    private LocalDate birthDate;

    @Size(max = 6)
    private String gender;

    @NotNull
    @Column(name = "registration_date")
    private LocalDate registrationDate;


    @Column(name = "allowed_courses")
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_courses",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")})
    private List<Course> allowedCourses;


    @Column(name = "allowed_sections")
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_sections",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "section_id")})
    private List<Section> allowedSections;


    @Column(name = "allowed_lessons")
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_lessons",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "lesson_id")})
    private List<Lesson> allowedLessons;


    @Column(name = "already_done_lesson")
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_already_done_lessons",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "lesson_id")})
    private List<Lesson> alreadyDoneLesson;

    @OneToOne(mappedBy = "author")
    private Comment comment;

    @Min(value = 0)
    @Max(value = 1000)
    private Long progress;
}
