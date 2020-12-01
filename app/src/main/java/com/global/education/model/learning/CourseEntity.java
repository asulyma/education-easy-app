package com.global.education.model.learning;

import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.education.common.model.InfoType;
import com.global.education.model.CreatableEntity;

import lombok.*;


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

	@Column(name = "finish_date")
	private Long finishDate;

	@Column(name = "cost")
	private Long cost;

	@Column(name = "additional_info", columnDefinition = "jsonb")
	@Type(type = "jsonb", parameters = { @Parameter(name = "classType", value = "java.util.HashMap") })
	private Map<InfoType, String> additionalInfo = new HashMap<>();

	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	private List<LessonEntity> lessons = new ArrayList<>();

}
