package com.global.education.service.specification;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class SpecificationCriteria implements Serializable {

    public String name;
    public Long age;
    public Long createdStartDate;
    public Long createdEndDate;

}
