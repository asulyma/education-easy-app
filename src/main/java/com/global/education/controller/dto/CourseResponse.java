package com.global.education.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Getter
@Setter
public class CourseResponse {

    private Long id;
    private Long createdDate;
    private String name;
    private String title;
    private String description;
    private LocalDate beginDate;
    private LocalDate endDate;
    private BigDecimal cost;
}
