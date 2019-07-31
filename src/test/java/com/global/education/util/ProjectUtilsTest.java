package com.global.education.util;

import com.global.education.controller.handler.exception.NotFoundRuntimeException;
import com.global.education.model.BaseEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;
import java.util.Random;

import static com.global.education.util.ProjectUtils.checkAndGetOptional;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class ProjectUtilsTest {

    @Test
    public void checkAndGetOptionalShouldPassed() {
        // PREDICATE
        Optional<BaseEntity> entity = Optional.of(new BaseEntity());
        Long randomId = new Random().nextLong();

        // TEST
        assertNotNull(checkAndGetOptional(entity, randomId));
    }

    @Test(expected = NotFoundRuntimeException.class)
    public void checkAndGetOptionalShouldFall() {
        // PREDICATE
        Optional<BaseEntity> entity = Optional.empty();
        Long randomId = new Random().nextLong();

        // TEST
        assertNull(checkAndGetOptional(entity, randomId));
    }

}
