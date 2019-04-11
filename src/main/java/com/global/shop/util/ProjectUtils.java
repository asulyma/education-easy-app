package com.global.shop.util;

import com.global.shop.exception.NotFoundRuntimeException;
import com.global.shop.model.user.Role;
import com.global.shop.model.user.UserEntity;
import com.global.shop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Optional;

/**
 * The class for the use of frequent logic, which will work in several classes.
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
     * The method for mapping the KeyCloak of the user and entity that is in the database. If user is exist in DB - only
     * returns instance. If not - create and persist to DB new UserEntity.
     * @param principal - the object to check security.
     * @return - the instance of UserEntity.
     */
    public UserEntity getUserInfo(Principal principal) {

        return null;

        //        OidcKeycloakAccount account = ((KeycloakAuthenticationToken) principal).getAccount();
        //        AccessToken token = account.getKeycloakSecurityContext().getToken();
        //
        //        UserEntity loginUser = userService.getUserByLogin(principal.getName());
        //        if (loginUser != null) {
        //            return loginUser;
        //        }
        //
        //        UserEntity newUser = new UserEntity();
        //
        //        newUser.setLogin(principal.getName());
        //        newUser.setEmail(token.getEmail());
        //        newUser.setGivenName(token.getGivenName());
        //        newUser.setGender(token.getGender());
        //        newUser.setFamilyName(token.getFamilyName());
        //        newUser.setRegistrationDate(LocalDate.now());
        //        newUser.setActive(true);
        //        newUser.setLocked(false);
        //        newUser.setRoles(String.join(", ", token.getRealmAccess().getRoles()));
        //
        //        UserEntity user = userService.createUser(newUser);
        //        log.info("UserEntity has been created: " + user.getLogin());
        //        return user;
    }

    public Long getUserAdminId() {
        UserEntity admin = Optional.ofNullable(userService.getUserByRole(Role.ADMIN.getDescription()))
                                   .orElseThrow(() -> new NotFoundRuntimeException(
                                           "No available user with role:" + Role.ADMIN.getDescription()));
        return admin.getId();
    }

}
