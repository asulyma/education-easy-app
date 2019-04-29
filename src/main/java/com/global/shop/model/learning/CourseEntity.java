package com.global.shop.model.learning;

import com.global.shop.model.CreatableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Entity
@Table(name = "course")
@NoArgsConstructor
@Getter
@Setter
public class CourseEntity extends CreatableEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "begin_date")
    private LocalDate beginDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "cost")
    private Integer cost;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<SectionEntity> sections;

}
