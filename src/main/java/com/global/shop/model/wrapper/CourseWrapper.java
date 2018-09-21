package com.global.shop.model.wrapper;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Getter
@Setter
public class CourseWrapper {

    private long id;
    private String name;
    private String title;
    private BigDecimal cost;
    private List<Long> allowedUsers;
}
