package com.global.shop.model.wrapper;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Getter
@Setter
public class LessonWrapper {

    private long id;
    private String title;
    private List<Long> allowedUsers;
    private List<Long> alreadyDone;
}
