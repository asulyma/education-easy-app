package com.education.common.kafka.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class UserFinishLessonEvent {

    private Long userId;
    private Long courseId;
    private Long alreadyDoneLesson;
    private Long coefficientToProgress;

}
