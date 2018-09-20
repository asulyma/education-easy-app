package com.global.shop.util;


import com.global.shop.model.user.User;
import com.global.shop.service.UserService;
import org.keycloak.adapters.OidcKeycloakAccount;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.security.Principal;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Component
@Scope("singleton")
public class ProjectUtils {

    private final UserService userService;

    @Autowired
    public ProjectUtils(UserService userService) {
        this.userService = userService;
    }


    public User getUser(Principal principal){

        OidcKeycloakAccount account = ((KeycloakAuthenticationToken) principal).getAccount();
        AccessToken token = account.getKeycloakSecurityContext().getToken();

        User loginUser = userService.getUserByLogin(principal.getName());
        if(loginUser != null){
            return loginUser;
        }

        User newUser = new User();

        //TODO build new user by token

        return userService.createUser(newUser);
    }

}
