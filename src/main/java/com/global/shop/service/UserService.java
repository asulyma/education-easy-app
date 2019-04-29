package com.global.shop.service;

import com.global.shop.exception.NotFoundRuntimeException;
import com.global.shop.model.user.Role;
import com.global.shop.model.user.UserEntity;
import com.global.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getListOfUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public UserEntity createUser(UserEntity userEntity) {
        return userRepository.saveAndFlush(userEntity);
    }

    public List<UserEntity> getUserByRole(Role role) {
        return userRepository.findByRoleContaining(role);
    }

    public UserEntity getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(NotFoundRuntimeException::new);
    }

}
