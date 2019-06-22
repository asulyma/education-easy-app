package com.global.education.service.specification;

import com.global.education.model.BaseEntity;
import com.global.education.service.specification.dto.UserSpecificationCriteria;
import org.springframework.data.jpa.domain.Specification;

import static com.global.education.util.SpecificationBuilderUtils.checkOnNotNull;

public class UserLevelSpecificationBuilder<R extends BaseEntity> extends CommonLevelSpecificationBuilder<R> {

    private static final String AGE = "age";

    public Specification<R> build(UserSpecificationCriteria criteria) {
        return Specification.where(super.build(criteria))
                            .and(buildAge(criteria));
    }

    private Specification<R> buildAge(UserSpecificationCriteria criteria) {
        return checkOnNotNull(criteria.getAge())
                ? (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(AGE), criteria.getAge())
                : null;
    }

}
