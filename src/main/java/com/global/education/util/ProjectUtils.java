package com.global.education.util;

import com.global.education.exception.NotAllowedRuntimeException;
import com.global.education.exception.NotFoundRuntimeException;
import com.global.education.model.BaseEntity;
import com.global.education.model.user.UserEntity;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class ProjectUtils {

    public static <T extends BaseEntity> T checkAndGetOptional(Optional<T> o, Long id) {
        return o.orElseThrow(() ->
                new NotFoundRuntimeException(o.getClass().getName() + " with id: " + id + " does not exist!"));
    }

    public static <T extends BaseEntity> T throwNotAllowed(T o, UserEntity user) {
        throw new NotAllowedRuntimeException(o.getClass().getName() + " with id " + o.getId()
                + " are not allowed for user: " + user.getLogin());
    }

}
