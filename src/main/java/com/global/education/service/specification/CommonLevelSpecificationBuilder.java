package com.global.education.service.specification;

import com.global.education.model.BaseEntity;
import com.global.education.service.specification.dto.CommonSpecificationCriteria;
import org.springframework.data.jpa.domain.Specification;

import static com.global.education.util.SpecificationBuilderUtils.checkOnNotNull;
import static com.global.education.util.SpecificationBuilderUtils.doubleCheckOnNotNull;

public abstract class CommonLevelSpecificationBuilder<R extends BaseEntity> {

    protected static final String CREATED_DATE = "createdDate";
    protected static final String NAME = "name";

    public Specification<R> build(CommonSpecificationCriteria criteria) {
        return Specification.where(buildCreatedDate(criteria))
                            .and(buildName(criteria));
    }

    protected Specification<R> buildCreatedDate(CommonSpecificationCriteria criteria) {
        return doubleCheckOnNotNull(criteria.getCreatedStartDate(), criteria.getCreatedEndDate())
                ? (root, criteriaQuery, cb) -> cb.between(root.get(CREATED_DATE),
                criteria.getCreatedStartDate(), criteria.getCreatedStartDate())
                : null;
    }

    protected Specification<R> buildName(CommonSpecificationCriteria criteria) {
        return checkOnNotNull(criteria.getName())
                ? (root, criteriaQuery, cb) -> cb.like(root.get(NAME), criteria.getName() + "%")
                : null;
    }

}
