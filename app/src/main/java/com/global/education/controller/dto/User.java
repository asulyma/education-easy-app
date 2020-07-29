package com.global.education.controller.dto;

import java.util.*;

import javax.validation.constraints.NotNull;

import com.education.common.model.Progress;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


@Getter
@Setter
@Accessors(chain = true)
public class User {

	private UUID uuid;

	private String username;
	@NotNull
	private String email;
	@NotNull
	private String rank;

	private Map<Long, Progress> progressMap = new HashMap<>();
}
