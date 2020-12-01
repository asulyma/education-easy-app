package com.global.education.service.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.global.education.model.UserDataEntity;
import com.global.education.service.specification.builder.UserSpecificationBuilder;


@Component
public class UserSpecificationFactory {

	private static final UserSpecificationBuilder<UserDataEntity> SPECIFICATION_BUILDER = new UserSpecificationBuilder<>();

	public Specification<UserDataEntity> build(SpecificationCriteria criteria) {
		return SPECIFICATION_BUILDER.build(criteria);
	}
}
