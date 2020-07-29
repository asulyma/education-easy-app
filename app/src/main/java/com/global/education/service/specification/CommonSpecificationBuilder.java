package com.global.education.service.specification;

import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

import static com.global.education.utils.SpecificationBuilderUtils.buildRange;

public abstract class CommonSpecificationBuilder<R> {

    private static final String CREATED_DATE = "createdDate";
    private static final String NAME = "name";

    public Specification<R> build(SpecificationCriteria criteria) {
        return Specification.where(buildCreatedDate(criteria))
                            .and(buildName(criteria));
    }

    protected Specification<R> buildCreatedDate(SpecificationCriteria criteria) {
        return buildRange(criteria.getCreatedStartDate(), criteria.getCreatedEndDate(),
                (root) -> root.get(CREATED_DATE));
    }

    protected Specification<R> buildName(SpecificationCriteria criteria) {
        return (root, cq, cb) -> Objects.isNull(criteria.getName())
                ? null
                : cb.like(root.get(NAME), criteria.getName() + "%");
    }

}
