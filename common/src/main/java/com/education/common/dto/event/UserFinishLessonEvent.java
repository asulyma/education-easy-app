package com.education.common.dto.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class UserFinishLessonEvent {

    private UUID userUuid;
    private Long courseId;
    private Long alreadyDoneLesson;
    private Long coefficientToProgress;

}
