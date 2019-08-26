package com.global.education.util;

import com.education.common.model.Progress;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class JacksonUtilsTest {

    @Test
    public void shouldTransformStringToJson() {
        // PREDICATE
        String testString = "test";

        // FUNCTIONALITY
        String result = JacksonUtils.toJsonString(testString);

        // TESTS
        assertNotNull(result);
        assertEquals("\"test\"", result);
    }

    @Test
    public void shouldTransformListToJson() {
        // PREDICATE
        List<String> list = Arrays.asList("test1", "test2");

        // FUNCTIONALITY
        String result = JacksonUtils.toJsonString(list);

        // TESTS
        assertNotNull(result);
        assertEquals("[\"test1\",\"test2\"]", result);
    }

    @Test
    public void shouldTransformMapToJson() {
        // PREDICATE
        Map<String, String> map = ImmutableMap.of("testKey1", "testValue1");

        // FUNCTIONALITY
        String result = JacksonUtils.toJsonString(map);

        // TESTS
        assertNotNull(result);
        assertEquals("{\"testKey1\":\"testValue1\"}", result);
    }

    @Test
    public void shouldTransformObjectToJson() {
        // PREDICATE
        Progress progress = new Progress()
                .setProgressValue(1L)
                .setAlreadyDoneLessons(Arrays.asList(1L, 2L, 3L));

        // FUNCTIONALITY
        String result = JacksonUtils.toJsonString(progress);

        // TESTS
        assertNotNull(result);
        assertEquals("{\"progressValue\":1,\"alreadyDoneLessons\":[1,2,3]}", result);
    }

}
