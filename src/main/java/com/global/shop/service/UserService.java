package com.global.shop.service;

import com.global.shop.model.user.User;

import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
public interface UserService {

    List<User> getListOfUsers();

    User getUserByLogin(String login);

    User createUser(User user);

}
