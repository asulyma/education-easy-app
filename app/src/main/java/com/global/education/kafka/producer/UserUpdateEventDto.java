package com.global.education.kafka.producer;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UserUpdateEventDto {

    private Long userId;
    private Long courseId;
    private Long alreadyDoneLesson;
    private Long coefficientToProgress;

}
