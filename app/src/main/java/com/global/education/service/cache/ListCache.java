package com.global.education.service.cache;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.global.education.controller.dto.SpecificationRequest;
import com.global.education.model.BaseEntity;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ListCache<E extends BaseEntity, SR extends SpecificationRequest> {

    @Getter
    private Map<SR, List<Long>> cache;

    private Function<SR, List<E>> findBySpecification;
    private Function<List<Long>, List<E>> findByIds;

    public void initCache(Function<SR, List<E>> bySpec, Function<List<Long>, List<E>> byIds) {
        cache = new HashMap<>();
        this.findBySpecification = bySpec;
        this.findByIds = byIds;
    }

    public List<E> getCache(SR request) {
        List<Long> ids = cache.get(request);
        if (Objects.isNull(ids)) {
            log.info("Put request to cache and execute without optimization");
            List<E> entities = findBySpecification.apply(request);
            putToCache(entities, request);
            return entities;
        }
        log.info("Request has been found in the cache, execute with optimization");
        return findByIds.apply(ids);
    }

    public void invalidateCache() {
        cache.clear();
        log.info("Cache has been invalidate");
    }

    private void putToCache(List<E> entities, SR request) {
        List<Long> entityIds = entities.stream().map(BaseEntity::getId).collect(Collectors.toList());
        cache.put(request, entityIds);
    }

}
