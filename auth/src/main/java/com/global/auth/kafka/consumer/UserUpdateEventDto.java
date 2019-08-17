package com.global.auth.kafka.consumer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateEventDto {

    private Long userId;
    private Long courseId;
    private Long alreadyDoneLesson;
    private Long coefficientToProgress;

}
