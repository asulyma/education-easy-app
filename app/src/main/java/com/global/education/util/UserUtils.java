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

/**
 * The class for the use of frequent logic, which will work in several classes.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserUtils {

    @Getter
    private static User currentUser;

    public static User currentUser() {

        if (currentUser == null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
            UsernamePasswordAuthenticationToken token =
                    (UsernamePasswordAuthenticationToken) oAuth2Authentication.getUserAuthentication();
            HashMap<String, Object> details = (HashMap<String, Object>) token.getDetails();
            HashMap<String, Object> principal = (HashMap<String, Object>) details.get("principal");
            HashMap<String, Object> userEntity = (HashMap<String, Object>) principal.get("userEntity");

            //        if(authentication instanceof null){
            //
            //        }
            currentUser = new User();
            //build
        }
        return currentUser;
    }

    public static Long currentUserId() {
        return currentUser().getId();
    }

    @Deprecated
    //change to isCurrentUserAdmin  Add name, UUID, login...
    public static Long getUserAdminId() {

        //todo find user where role = admin

        return null;
    }

}
