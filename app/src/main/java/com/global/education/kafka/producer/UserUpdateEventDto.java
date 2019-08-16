package com.global.education.kafka.producer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserUpdateEventDto {

    private Long userId;
    private Long courseId;

}
