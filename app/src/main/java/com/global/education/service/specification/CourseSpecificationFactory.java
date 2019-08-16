package com.global.education.service.specification;

import com.global.education.model.learning.CourseEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CourseSpecificationFactory {

    private static final CourseSpecificationBuilder<CourseEntity> SPECIFICATION_BUILDER =
            new CourseSpecificationBuilder<>();

    public Specification<CourseEntity> build(SpecificationCriteria criteria) {
        return SPECIFICATION_BUILDER.build(criteria);
    }

}
