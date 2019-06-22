package com.global.education.transformer;

import com.global.education.controller.dto.UserSpecificationRequest;
import com.global.education.service.specification.dto.UserSpecificationCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserSpecificationTransformer {

    @Autowired
    private CommonSpecificationTransformer commonSpecificationTransformer;

    public UserSpecificationCriteria buildSpecificationCriteria(UserSpecificationRequest request) {
        UserSpecificationCriteria criteria = new UserSpecificationCriteria();
        commonSpecificationTransformer.buildCriteria(criteria, request);
        criteria.setAge(request.getAge());
        return criteria;
    }

}
