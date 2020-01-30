package com.global.education.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SpecificationBuilderUtils {

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

    public interface RootCriteriaFunction<R> {

        Expression<Long> apply(Root<R> root);

    }

}
