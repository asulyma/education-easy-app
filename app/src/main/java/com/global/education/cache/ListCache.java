package com.global.education.cache;

import com.global.education.controller.dto.SpecificationRequest;
import com.global.education.model.BaseEntity;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class ListCache<E extends BaseEntity, SR extends SpecificationRequest> {

    @Getter
    private Map<SR, List<Long>> cache = new HashMap<>();

    private Function<SR, List<E>> findBySpecification;
    private Function<List<Long>, List<E>> findByIds;

    public void initCache(Function<SR, List<E>> bySpec, Function<List<Long>, List<E>> byIds) {
        this.findBySpecification = bySpec;
        this.findByIds = byIds;
    }

    public List<E> getCache(SR request) {
        List<Long> ids = cache.get(request);
        if (Objects.isNull(ids)) {
            List<E> entities = findBySpecification.apply(request);
            putToCache(entities, request);
            return entities;
        }
        return findByIds.apply(ids);

    }

    private void putToCache(List<E> entities, SR request) {
        cache.put(request, entities.stream()
                                   .map(BaseEntity::getId)
                                   .collect(Collectors.toList()));
    }

}
