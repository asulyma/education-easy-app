package com.global.education.service.specification;

import com.global.education.model.user.UserEntity;
import com.global.education.service.specification.dto.UserSpecificationCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class UserSpecificationBuilder {

    private static final UserLevelSpecificationBuilder<UserEntity> SPECIFICATION_BUILDER =
            new UserLevelSpecificationBuilder<>();

    public Specification<UserEntity> build(UserSpecificationCriteria criteria) {
        return SPECIFICATION_BUILDER.build(criteria);
    }

}
