package com.global.education.service.specification.builder;

import static com.global.education.utils.SpecificationBuilderUtils.buildKeyInsensitiveSearch;
import static com.global.education.utils.SpecificationBuilderUtils.buildRange;
import static java.util.Objects.isNull;

import org.springframework.data.jpa.domain.Specification;

import com.global.education.service.specification.SpecificationCriteria;


public class CourseSpecificationBuilder<R> extends CommonSpecificationBuilder<R> {

	private static final String COST = "cost";
	private static final String BEGIN_DATE = "beginDate";
	private static final String FINISH_DATE = "finishDate";
	private static final String TITLE = "title";

	public Specification<R> build(SpecificationCriteria criteria) {
		return Specification.where(super.build(criteria))
				.and(buildCostRange(criteria))
				.and(buildBeginDate(criteria))
				.and(buildFinishDate(criteria))
				.and(buildTitle(criteria));
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

	private Specification<R> buildTitle(SpecificationCriteria criteria) {
		return isNull(criteria.getTitle()) ? null : buildKeyInsensitiveSearch(TITLE, criteria.getTitle());
	}

}
