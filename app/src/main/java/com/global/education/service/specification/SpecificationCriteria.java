package com.global.education.service.specification;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class SpecificationCriteria implements Serializable {

    public String name;
    public Long age;
    public Long createdStartDate;
    public Long createdEndDate;

}
