package com.global.education.model.wrapper;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Getter
@Setter
public class SectionResponse {

    private Long id;
    private String title;
    private String description;
    private Long courseId;
}
