package com.global.shop.util;


import com.global.shop.model.user.Role;
import com.global.shop.model.user.User;
import com.global.shop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.adapters.OidcKeycloakAccount;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Optional;

/**
 * The class for the use of frequent logic, which will work in several classes.
 *
 * @author Aleksandr Sulyma
 * @version 1.0
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
     * The method for mapping the KeyCloak of the user and entity that is in the database.
     * If user is exist in DB - only returns instance.
     * If not - create and persist to DB new User.
     *
     * @param principal - the object to check security.
     *
     * @return - the instance of User.
     */
    public User getUserInfo(Principal principal) {

        OidcKeycloakAccount account = ((KeycloakAuthenticationToken) principal).getAccount();
        AccessToken token = account.getKeycloakSecurityContext().getToken();

        User loginUser = userService.getUserByLogin(principal.getName());
        if (loginUser != null) {
            log.info("User with id: " + loginUser.getId() + " is already exist.");
            return loginUser;
        }

        User newUser = new User();

        newUser.setLogin(principal.getName());
        newUser.setEmail(token.getEmail());
        newUser.setGivenName(token.getGivenName());
        newUser.setGender(token.getGender());
        newUser.setFamilyName(token.getFamilyName());
        newUser.setRegistrationDate(LocalDate.now());
        newUser.setActive(true);
        newUser.setLocked(false);
        newUser.setRoles(String.join(", ", token.getRealmAccess().getRoles()));

        return userService.createUser(newUser);
    }

    //TODO refactoring
    public Long getUserAdminId() {
        User admin = Optional.ofNullable(userService.getUserByRole(Role.ADMIN.getDescription()))
                .orElseThrow(RuntimeException::new);
        return admin.getId();
    }

}
