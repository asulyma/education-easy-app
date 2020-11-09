package com.education.common.dto.event;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
public class UserFinishLessonEvent extends UserToCourseEvent {

	private Long alreadyDoneLesson;
	private Long coefficientToProgress;

}
