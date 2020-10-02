package com.global.education.service.specification;

import static com.global.education.utils.SpecificationBuilderUtils.buildRange;

import java.util.Objects;

import org.springframework.data.jpa.domain.Specification;


public abstract class CommonSpecificationBuilder<R> {

	private static final String CREATED_DATE = "createdDate";
	private static final String TITLE = "title";

	public Specification<R> build(SpecificationCriteria criteria) {
		return Specification.where(buildCreatedDate(criteria)).and(buildTitle(criteria));
	}

	protected Specification<R> buildCreatedDate(SpecificationCriteria criteria) {
		return buildRange(criteria.getCreatedStartDate(), criteria.getCreatedEndDate(), (root) -> root.get(CREATED_DATE));
	}

	/**
	 * Key insensitive search by TITLE field.
	 */
	protected Specification<R> buildTitle(SpecificationCriteria criteria) {
		return (root, cq, cb) -> Objects.isNull(criteria.getTitle())
				? null
				: cb.like(cb.lower(root.get(TITLE)), "%" + criteria.getTitle() + "%");
	}

}
