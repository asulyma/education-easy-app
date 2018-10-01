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
public class SectionWrapper {

    private Long id;
    private String title;
    private List<Long> allowedUsers;
}
