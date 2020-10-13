package com.global.education.controller.dto;

import javax.validation.constraints.NotNull;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

	@NotNull
	private Long lessonId;
	@NotNull
	private Long courseId;
	@NotNull
	private String content;

}
