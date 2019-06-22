package com.global.education.transformer;

import com.global.education.controller.dto.CommonSpecificationRequest;
import com.global.education.service.specification.dto.CommonSpecificationCriteria;
import org.springframework.stereotype.Component;

@Component
public class CommonSpecificationTransformer {

    public CommonSpecificationCriteria buildCriteria(CommonSpecificationCriteria criteria,
            CommonSpecificationRequest request) {
        criteria.setCreatedStartDate(request.getCreatedStartDate());
        criteria.setCreatedEndDate(request.getCreatedEndDate());
        criteria.setName(request.getName());
        return criteria;
    }

}
