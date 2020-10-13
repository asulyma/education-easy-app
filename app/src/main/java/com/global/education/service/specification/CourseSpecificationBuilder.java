package com.global.education.service.specification;

import static com.global.education.utils.SpecificationBuilderUtils.buildRange;

import org.springframework.data.jpa.domain.Specification;


public class CourseSpecificationBuilder<R> extends CommonSpecificationBuilder<R> {

	private static final String COST = "cost";
	private static final String BEGIN_DATE = "beginDate";
	private static final String FINISH_DATE = "finishDate";

	public Specification<R> build(SpecificationCriteria criteria) {
		return Specification.where(super.build(criteria))
				.and(buildCostRange(criteria))
				.and(buildBeginDate(criteria))
				.and(buildFinishDate(criteria));
	}

	private Specification<R> buildCostRange(SpecificationCriteria criteria) {
		return buildRange(criteria.getCostStart(), criteria.getCostEnd(), (r) -> r.get(COST));
	}

	private Specification<R> buildBeginDate(SpecificationCriteria criteria) {
		return buildRange(criteria.getBeginStartDate(), criteria.getBeginEndDate(), (r) -> r.get(BEGIN_DATE));
	}

	private Specification<R> buildFinishDate(SpecificationCriteria criteria) {
		return buildRange(criteria.getFinishStartDate(), criteria.getFinishEndDate(), (r) -> r.get(FINISH_DATE));
	}

}
