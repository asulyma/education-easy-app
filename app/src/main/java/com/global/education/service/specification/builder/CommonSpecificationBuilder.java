package com.global.education.service.specification.builder;

import static com.global.education.utils.SpecificationBuilderUtils.buildRange;

import org.springframework.data.jpa.domain.Specification;

import com.global.education.service.specification.SpecificationCriteria;


public abstract class CommonSpecificationBuilder<R> {

	private static final String CREATED_DATE = "createdDate";

	public Specification<R> build(SpecificationCriteria criteria) {
		return Specification.where(buildCreatedDate(criteria));
	}

	protected Specification<R> buildCreatedDate(SpecificationCriteria criteria) {
		return buildRange(criteria.getCreatedStartDate(), criteria.getCreatedEndDate(), (root) -> root.get(CREATED_DATE));
	}

}
