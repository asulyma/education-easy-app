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
public class SectionViewWrapper {

    private Long id;
    private String title;
    private String description;
    private Long courseId;
    private List<Long> allowedUsers;

}
