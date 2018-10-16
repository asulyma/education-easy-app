package com.global.shop.model.wrapper;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Getter
@Setter
public class LessonWrapper {

    private Long id;
    private String title;
    private LocalDate updateDate;
    private Long countOfComments;
}
