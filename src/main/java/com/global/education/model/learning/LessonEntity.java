package com.global.education.model.learning;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.global.education.model.CreatableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
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
@Table(name = "lesson")
@NoArgsConstructor
@Getter
@Setter
public class LessonEntity extends CreatableEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private SectionEntity section;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "lesson", allowSetters = true)
    private List<Comment> comment = new ArrayList<>();
}
