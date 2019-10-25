package com.global.education.util;

import com.global.education.controller.dto.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * The class for the use of frequent logic, which will work in several classes.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserUtils {

    @SuppressWarnings("unchecked")
    public static User currentUser() {

        //todo parse token and return UUID of user
        return null;
    }

    public static UUID currentUserUuid() {
        return currentUser().getUuid();
    }

    @Deprecated
    public static Long getUserAdminId() {

        //todo find user where role = admin

        return null;
    }

}
