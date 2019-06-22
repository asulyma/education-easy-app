package com.global.education.controller.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@EqualsAndHashCode
public class CommonSpecificationRequest implements Serializable {

    private String name;

    @Min(value = 0)
    @Max(value = Long.MAX_VALUE)
    private Long createdStartDate;
    @Min(value = 0)
    @Max(value = Long.MAX_VALUE)
    private Long createdEndDate;

}
