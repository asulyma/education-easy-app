package com.global.education.transformer;

import com.global.education.controller.dto.SpecificationRequest;
import com.global.education.service.specification.SpecificationCriteria;
import org.springframework.stereotype.Component;

@Component
public class SpecificationTransformer {

    public SpecificationCriteria buildSpecificationCriteria(SpecificationRequest request) {
        return SpecificationCriteria.builder()
                                    .name(request.getName())
                                    .age(request.getAge())
                                    .createdStartDate(request.getCreatedStartDate())
                                    .createdEndDate(request.getCreatedEndDate())
                                    .build();
    }

}
