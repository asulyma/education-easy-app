package com.global.education.service.cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.global.education.controller.dto.SpecificationRequest;
import com.global.education.model.BaseEntity;


@RunWith(MockitoJUnitRunner.class)
public class ListCacheTest {

	private static final String TEST_VALUE = "0000000";
	private static final String TEST_VALUE_2 = "1111111";

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
		assertTrue(listCache.getCache().isEmpty());

		// FUNCTIONALITY
		SpecificationRequest request = buildRequest(TEST_VALUE);
		List<BaseEntity> resultEntities = listCache.getCache(request);

		// TESTS
		assertEquals(1, resultEntities.size());
		assertEquals(1, listCache.getCache().size());

		// FUNCTIONALITY second call
		List<BaseEntity> resultEntities2 = listCache.getCache(request);

		//TESTS using cache
		assertEquals(1, resultEntities2.size());
		assertEquals(1, listCache.getCache().size());

		// FUNCTIONALITY third call new request
		SpecificationRequest request2 = buildRequest(TEST_VALUE_2);
		List<BaseEntity> resultEntities3 = listCache.getCache(request2);

		// TESTS
		assertEquals(1, resultEntities3.size());
		assertEquals(2, listCache.getCache().size());
	}

	private SpecificationRequest buildRequest(String title) {
		SpecificationRequest request = new SpecificationRequest();
		request.setTitle(title);
		return request;
	}

	private List findBySpec(SpecificationRequest request) {
		return Collections.singletonList(new BaseEntity());
	}

	private List findByIds(List<Long> ids) {
		return Collections.singletonList(new BaseEntity());
	}

}
