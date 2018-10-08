package com.global.shop.model.wrapper;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Getter
@Setter
public class CourseWrapper {

    private Long id;
    private String name;
    private String title;
    private BigDecimal cost;
}
