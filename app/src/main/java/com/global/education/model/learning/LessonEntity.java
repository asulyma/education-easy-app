package com.global.education.model.learning;

import com.global.education.model.CreatableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "lesson")
@Accessors(chain = true)
public class LessonEntity extends CreatableEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    private List<CommentEntity> comments = new ArrayList<>();
}
