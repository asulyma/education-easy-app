package com.global.education.controller.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class UserSpecificationRequest extends CommonSpecificationRequest {

    @Min(value = 0)
    private Integer age;

}
