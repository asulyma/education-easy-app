package com.global.education.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private UUID authorUuid;
    @NotNull
    private Long lessonId;
    @NotNull
    private Long courseId;
    @NotNull
    private String content;

}
