package com.global.education.service.specification;

import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class UserSpecificationBuilder<R> extends CommonSpecificationBuilder<R> {

    private static final String AGE = "age";

    public Specification<R> build(SpecificationCriteria criteria) {
        return Specification.where(super.build(criteria))
                            .and(buildAge(criteria));
    }

    private Specification<R> buildAge(SpecificationCriteria criteria) {
        return (root, cq, cb) -> Objects.isNull(criteria.getAge())
                ? null
                : cb.equal(root.get(AGE), criteria.getAge());
    }

}
