package com.global.education.service.specification.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class CommonSpecificationCriteria implements Serializable {

    public Long createdStartDate;
    public Long createdEndDate;
    public String name;

}
