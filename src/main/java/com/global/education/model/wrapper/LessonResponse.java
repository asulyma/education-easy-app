package com.global.education.model.wrapper;

import com.global.education.model.learning.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Getter
@Setter
public class LessonResponse {

    private Long id;
    private String title;
    private String description;
    private LocalDate updateDate;
    private Long sectionId;
    private List<Long> alreadyDoneIds;
    private List<Comment> comment;
}
