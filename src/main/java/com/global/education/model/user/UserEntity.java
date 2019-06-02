package com.global.education.model.user;

import com.global.education.model.CreatableEntity;
import com.global.education.model.learning.Comment;
import com.global.education.model.learning.CourseEntity;
import com.global.education.model.learning.LessonEntity;
import com.global.education.model.learning.Progress;
import com.global.education.model.learning.SectionEntity;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

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
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class UserEntity extends CreatableEntity {

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
    private List<CourseEntity> allowedCourses;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_sections",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "section_id")})
    private List<SectionEntity> allowedSections;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_already_done_lessons",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "lesson_id")})
    private List<LessonEntity> alreadyDoneLessons;

    @OneToOne(mappedBy = "author")
    private Comment comment;

    @Type(type = "jsonb")
    @Column(name = "progress", columnDefinition = "jsonb")
    private Progress progress;
}
