package com.global.shop.model.wrapper;

import com.global.shop.model.learning.Comment;
import com.global.shop.model.learning.Like;
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
public class LessonViewWrapper {

    private Long id;
    private String title;
    private String description;
    private LocalDate updateDate;
    private Long sectionId;
    private List<Long> alreadyDoneIds;
    private Like lessonLike;
    private List<Comment> comment;

}
