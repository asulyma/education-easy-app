package com.global.education.controller.dto;

import java.util.UUID;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SharedComment extends Comment {

	private Long id;
	private Long createdDate;
	private UUID authorUuid;
	private String authorName;

}
