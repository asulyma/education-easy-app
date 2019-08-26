package com.global.education;

import com.global.education.cache.ListCache;
import com.global.education.controller.dto.SpecificationRequest;
import com.global.education.model.BaseEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ListCacheTest {

    @Mock
    private ListCache<BaseEntity, SpecificationRequest> listCache;

    @Before
    public void setUp() {
        listCache = new ListCache<>();
    }

    @Test
    public void shouldExecuteWithoutCache() {
        // PREDICATE
        listCache.initCache(this::findBySpec, this::findByIds);
        SpecificationRequest request = buildRequest();

        // TESTS
        assertTrue(listCache.getCache().isEmpty());

        // FUNCTIONALITY
        List<BaseEntity> cache = listCache.getCache(request);

        // TESTS
        assertEquals(1, cache.size());

        // FUNCTIONALITY
        listCache.initCache(null, this::findByIds);
        List<BaseEntity> cache2 = listCache.getCache(request);

        //TESTS using cache
        assertEquals(1, cache.size());
    }

    private SpecificationRequest buildRequest() {
        SpecificationRequest request = new SpecificationRequest();
        request.setName("TEST_NAME");
        return request;
    }

    private List findBySpec(SpecificationRequest request) {
        return Collections.singletonList(new BaseEntity());
    }

    private List findByIds(List<Long> ids) {
        return Collections.singletonList(new BaseEntity());
    }

}
