package com.global.shop.model.wrapper;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Getter
@Setter
public class CourseViewWrapper {

    private Long id;
    private String name;
    private String title;
    private String description;
    private LocalDate beginDate;
    private LocalDate endDate;
    private BigDecimal cost;
    private List<Long> allowedUsers;

}
