package com.global.shop.model.wrapper;

import com.global.shop.model.learning.Comment;
import com.global.shop.model.learning.Like;
import com.global.shop.model.learning.Section;
import com.global.shop.model.user.User;
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
    private Section section;
    private List<User> allowedUsers;
    private List<User> alreadyDone;
    private Like lessonLike;
    private List<Comment> comment;

}
