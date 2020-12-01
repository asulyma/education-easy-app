package com.education.common.dto.event;

import java.util.UUID;

import lombok.*;
import lombok.experimental.Accessors;


@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserToCourseEvent {

	private UUID userUuid;
	private Long courseId;

}
