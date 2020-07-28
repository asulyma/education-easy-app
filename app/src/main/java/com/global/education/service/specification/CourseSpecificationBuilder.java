package com.global.education.service.specification;

import org.springframework.data.jpa.domain.Specification;

import static com.global.education.utils.SpecificationBuilderUtils.buildRange;

public class CourseSpecificationBuilder<R> extends CommonSpecificationBuilder<R> {

    private static final String COST = "cost";

    public Specification<R> build(SpecificationCriteria criteria) {
        return Specification.where(super.build(criteria))
                            .and(buildCostRange(criteria));
    }

    private Specification<R> buildCostRange(SpecificationCriteria criteria) {
        return buildRange(criteria.getCostStart(), criteria.getCostEnd(), (r) -> r.get(COST));
    }

}
