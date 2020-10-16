package com.global.education.controller.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.*;


@Getter
@Setter
@EqualsAndHashCode
public class SpecificationRequest implements Serializable {

	private String title;

	@Min(value = 0)
	@Max(value = Long.MAX_VALUE)
	private Long costStart;
	@Min(value = 0)
	@Max(value = Long.MAX_VALUE)
	private Long costEnd;

	@Min(value = 0)
	@Max(value = Long.MAX_VALUE)
	private Long createdStartDate;
	@Min(value = 0)
	@Max(value = Long.MAX_VALUE)
	private Long createdEndDate;

	@Min(value = 0)
	@Max(value = Long.MAX_VALUE)
	private Long beginStartDate;
	@Min(value = 0)
	@Max(value = Long.MAX_VALUE)
	private Long beginEndDate;

	@Min(value = 0)
	@Max(value = Long.MAX_VALUE)
	private Long finishStartDate;
	@Min(value = 0)
	@Max(value = Long.MAX_VALUE)
	private Long finishEndDate;

	private Long usersByCourseId;
	private String userRank;
	private String userName;

}
