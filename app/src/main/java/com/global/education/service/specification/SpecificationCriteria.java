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

    public String title;
    public Long costStart;
    public Long costEnd;
    public Long createdStartDate;
    public Long createdEndDate;

    public Long beginStartDate;
    public Long beginEndDate;

    public Long finishStartDate;
    public Long finishEndDate;

    public Long usersByCourseId;
    private String userRank;
    private String userName;

}
