package com.global.education.util;

import com.global.education.controller.handler.exception.NotAllowedRuntimeException;
import com.global.education.controller.handler.exception.NotFoundRuntimeException;
import com.global.education.model.BaseEntity;
import com.global.education.model.user.UserEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
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
