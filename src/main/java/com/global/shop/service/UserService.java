package com.global.shop.service;

import com.global.shop.model.user.User;
import com.global.shop.model.wrapper.UserWrapper;

import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
public interface UserService {

    List<UserWrapper> getListOfUsers();

    User getUserByLogin(String login);

    User createUser(User user);

    User getUserByRole(String role);

}
