package com.global.shop.model.learning;

import com.global.shop.model.CreatableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Entity
@Table(name = "section")
@NoArgsConstructor
@Getter
@Setter
public class SectionEntity extends CreatableEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @OneToMany(mappedBy = "sectionEntity", cascade = CascadeType.ALL)
    private List<LessonEntity> lessons;

}
