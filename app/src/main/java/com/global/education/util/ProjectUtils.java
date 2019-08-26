package com.global.education.util;

import com.global.education.controller.dto.User;
import com.global.education.controller.handler.exception.NotAllowedRuntimeException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProjectUtils {

    public static final Long TOTAL_PROGRESS = 1000L;
    public static final String ID_REGEXP = "^[0-9]{1,9}";

    public static void checkOnStartCourse(Long courseId, User user) {
        if (user.getProgressMap().containsKey(courseId)) {
            return;
        }
        throw new NotAllowedRuntimeException(
                "User with id " + user.getId() + " did not start course with id " + courseId);
    }

}
