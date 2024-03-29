package com.global.education.utils;

import java.util.Objects;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SpecificationBuilderUtils {

	public static final String PERCENT = "%";

	public static <R> Specification<R> buildRange(Long start, Long end, RootCriteriaFunction<R> rootFunc) {
		Specification<R> result = Specification.where(null);

		if (Objects.nonNull(start)) {
			result = result.and((root, cq, cb) -> cb.ge(rootFunc.apply(root), start));
		}
		if (Objects.nonNull(end)) {
			result = result.and(((root, cq, cb) -> cb.le(rootFunc.apply(root), end)));
		}
		return result;
	}

	public static <R> Specification<R> buildKeyInsensitiveSearch(String field, String value) {
		return (root, cq, cb) -> cb.like(cb.lower(root.get(field)), PERCENT + value.toLowerCase() + PERCENT);
	}

	public interface RootCriteriaFunction<R> {
		Expression<Long> apply(Root<R> root);
	}

}
