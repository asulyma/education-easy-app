package com.global.education.controller.dto;

import java.util.Map;

import com.education.common.model.InfoType;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SharedCourse extends Course {

	private Long createdDate;
	private Map<InfoType, String> additionalInfo;

}
