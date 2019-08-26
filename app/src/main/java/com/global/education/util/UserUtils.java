package com.global.education.util;

import com.global.education.controller.dto.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The class for the use of frequent logic, which will work in several classes.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserUtils {

    private static final String ID = "id";
    private static final String USERNAME = "username";
    private static final String ROLES = "roles";
    private static final String EMAIL = "email";
    private static final String RANK = "rank";
    private static final String PROGRESS_MAP = "progressMap";

    @Getter
    private static User currentUser;

    @SuppressWarnings("unchecked")
    public static User currentUser() {

        if (currentUser == null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
            UsernamePasswordAuthenticationToken token =
                    (UsernamePasswordAuthenticationToken) oAuth2Authentication.getUserAuthentication();
            HashMap<String, Object> details = (HashMap<String, Object>) token.getDetails();
            HashMap<String, Object> principal = (HashMap<String, Object>) details.get("principal");
            HashMap<String, Object> userInstance = (HashMap<String, Object>) principal.get("userEntity");

            currentUser = buildCurrentUser(userInstance);
        }
        return currentUser;
    }

    public static Long currentUserId() {
        return currentUser().getId();
    }

    private static User buildCurrentUser(Map<String, Object> userInstance) {
        return new User()
                .setId(Long.parseLong(String.valueOf(userInstance.get(ID))))
                .setUsername((String) userInstance.get(USERNAME))
                .setEmail((String) userInstance.get(EMAIL))
                .setRoles(getRoles(userInstance))
                .setRank((String) userInstance.get(RANK))
                .setProgressMap(null);      //todo
    }

    @SuppressWarnings("unchecked")
    private static Set<String> getRoles(Map<String, Object> userInstance) {
        List<Map<String, String>> roles = (List<Map<String, String>>) userInstance.get(ROLES);
        return roles.stream()
                    .map(e -> e.get("authority"))
                    .collect(Collectors.toSet());

    }

    @Deprecated
    //change to isCurrentUserAdmin  Add name, UUID, login...
    public static Long getUserAdminId() {

        //todo find user where role = admin

        return null;
    }

}
