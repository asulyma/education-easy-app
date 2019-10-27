package com.global.education.model.learning;

import com.global.education.model.CreatableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "course")
@NoArgsConstructor
public class CourseEntity extends CreatableEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "begin_date")
    private Long beginDate;

    @Column(name = "end_date")
    private Long endDate;

    @Column(name = "cost")
    private Long cost;

    @Column(name = "additional_info")
    private String additionalInfo;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<LessonEntity> lessons = new ArrayList<>();

}
