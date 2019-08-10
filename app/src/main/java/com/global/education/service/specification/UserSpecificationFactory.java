package com.global.education.service.specification;

import com.global.education.model.user.UserEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class UserSpecificationFactory {

    private static final UserSpecificationBuilder<UserEntity> SPECIFICATION_BUILDER =
            new UserSpecificationBuilder<>();

    public Specification<UserEntity> build(SpecificationCriteria criteria) {
        return SPECIFICATION_BUILDER.build(criteria);
    }

}
