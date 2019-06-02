package com.global.education.util;

import com.global.education.exception.NotFoundRuntimeException;
import com.global.education.model.user.Role;
import com.global.education.model.user.UserEntity;
import com.global.education.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Principal;

/**
 * The class for the use of frequent logic, which will work in several classes.
 */
@Component
@Scope("singleton")
@Slf4j
public class ProjectUtils {

    private final UserService userService;

    @Autowired
    public ProjectUtils(UserService userService) {
        this.userService = userService;
    }

    /**
     * The method for mapping the KeyCloak of the user and entity that is in the database. If user is exist in DB - only
     * returns instance. If not - create and persist to DB new UserEntity.
     *
     * @param principal - the object to check security.
     * @return the instance of UserEntity.
     */
    public UserEntity getUserInfo(Principal principal) {
        Object tokenObject = ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        User securityUser = (User) tokenObject;

        UserEntity loginUser = userService.getUserByLogin(securityUser.getUsername());
        if (loginUser != null) {
            return loginUser;
        }

        UserEntity newUser = new UserEntity();
        newUser.setLogin(securityUser.getUsername());
        newUser.setActive(true);

        String role = securityUser.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse(StringUtils.EMPTY);
        newUser.setRole(Role.valueOf(role));

        UserEntity user = userService.createUser(newUser);
        log.info("UserEntity has been created: " + user.getLogin());
        return user;
    }

    public Long getUserAdminId() {
        UserEntity admin = userService.getUserByRole(Role.ADMIN)
                .stream()
                .findFirst()
                .orElseThrow(NotFoundRuntimeException::new);

        return admin.getId();
    }

}
