package com.global.shop.model.user;

import com.global.shop.model.CreatableEntity;
import com.global.shop.model.learning.Comment;
import com.global.shop.model.learning.Course;
import com.global.shop.model.learning.Lesson;
import com.global.shop.model.learning.Progress;
import com.global.shop.model.learning.Section;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_table")
@NoArgsConstructor
@Getter
@Setter
public class User extends CreatableEntity {

    @Column(name = "login")
    private String login;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "email")
    private String email;

    @Column(name = "active")
    private boolean active;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "rank")
    private Rank rank;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "gender")
    private String gender;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_course",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")})
    private List<Course> allowedCourses;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_sections",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "section_id")})
    private List<Section> allowedSections;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_already_done_lessons",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "lesson_id")})
    private List<Lesson> alreadyDoneLesson;

    @OneToOne(mappedBy = "author")
    private Comment comment;

    @Column(name = "progress", columnDefinition = "jsonb")
    @Type(type = "jsonb", parameters = {@Parameter(name = "classType",
            value = "com.global.shop.model.learning.Progress")})
    private Progress progress;
}
