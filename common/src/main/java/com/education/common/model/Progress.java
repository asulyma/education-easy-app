package com.education.common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.*;
import lombok.experimental.Accessors;


@Getter
@Setter
@AllArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor
public class Progress implements Serializable {

	private long progressValue;
	private long totalValue;
	private List<Long> alreadyDoneLessons = new ArrayList<>();
	private boolean finish;
	private String passedDate;

	public Progress(long totalValue) {
		this.totalValue = totalValue;
	}

}
