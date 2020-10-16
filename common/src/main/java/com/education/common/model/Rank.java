package com.education.common.model;

import java.util.Arrays;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Rank {

	TRAINEE("Trainee"),
	JUNIOR("Junior"),
	UPPER_JUNIOR("Upper Junior"),
	MIDDLE("Middle"),
	UPPER_MIDDLE("Upper Middle"),
	SENIOR("Senior"),
	UNDEFINED("undefined");

	@Getter
	private final String description;

	public static Rank getRank(String input) {
		return Arrays.stream(values())
				.filter(e -> e.getDescription().equalsIgnoreCase(input))
				.findFirst()
				.orElse(UNDEFINED);
	}
}
