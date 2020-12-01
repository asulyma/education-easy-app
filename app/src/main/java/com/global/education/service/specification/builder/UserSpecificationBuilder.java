package com.global.education.service.specification.builder;

import static com.education.common.model.Rank.getRank;
import static com.global.education.service.specification.dialect.EducationPostgreSQL95Dialect.JSONB_CONTAINS;
import static com.global.education.utils.SpecificationBuilderUtils.buildKeyInsensitiveSearch;
import static java.util.Objects.isNull;

import org.springframework.data.jpa.domain.Specification;

import com.global.education.service.specification.SpecificationCriteria;


public class UserSpecificationBuilder<R> extends CommonSpecificationBuilder<R> {

	private static final String PROGRESS_MAP = "progressMap";
	private static final String RANK = "rank";
	private static final String USERNAME = "username";

	public Specification<R> build(SpecificationCriteria criteria) {
		return Specification.where(super.build(criteria))
				.and(buildUserName(criteria))
				.and(buildUserRank(criteria))
				.and(buildUsersByCourse(criteria));
	}

	private Specification<R> buildUserName(SpecificationCriteria criteria) {
		return isNull(criteria.getUserName()) ? null : buildKeyInsensitiveSearch(USERNAME, criteria.getUserName());
	}

	private Specification<R> buildUserRank(SpecificationCriteria criteria) {
		String rank = criteria.getUserRank();
		return isNull(rank) ? null : (root, cq, cb) -> cb.equal(root.get(RANK), getRank(rank));
	}

	private Specification<R> buildUsersByCourse(SpecificationCriteria criteria) {
		Long courseId = criteria.getUsersByCourseId();
		if (isNull(courseId)) {
			return null;
		}
		return (root, cq, cb) -> cb.isTrue(
				cb.function(JSONB_CONTAINS, Boolean.class, root.get(PROGRESS_MAP), cb.literal(courseId)));
	}

}
