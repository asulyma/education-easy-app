package com.global.education.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SharedCourse extends Course {

    private Long createdDate;
    private String additionalInfo;

}
