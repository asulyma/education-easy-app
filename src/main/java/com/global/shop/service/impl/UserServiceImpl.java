package com.global.shop.service.impl;

import com.global.shop.model.user.User;
import com.global.shop.model.wrapper.UserWrapper;
import com.global.shop.repository.UserRepository;
import com.global.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserWrapper> getListOfUsers() {
        List<User> users = userRepository.findAll();
        return buildUserWrappers(users);
    }

    @Override
    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User createUser(User user) {
        return userRepository.saveAndFlush(user);
    }


    private List<UserWrapper> buildUserWrappers(List<User> users) {

        List<UserWrapper> wrappers = new ArrayList<>();
        users.forEach(user -> {
            UserWrapper wrapper = new UserWrapper();
            wrapper.setId(user.getId());
            wrapper.setLogin(user.getLogin());
            wrapper.setEmail(user.getEmail());
            wrapper.setFamilyName(user.getFamilyName());
            wrapper.setGivenName(user.getGivenName());
            wrapper.setRegistrationDate(user.getRegistrationDate());
            wrapper.setActive(user.isActive());
            wrapper.setLocked(user.isLocked());
            wrappers.add(wrapper);
        });
        return wrappers;
    }
}
