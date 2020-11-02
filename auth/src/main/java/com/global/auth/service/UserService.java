package com.global.auth.service;

import com.education.common.dto.event.UserDataEvent;
import com.global.auth.model.UserEntity;
import com.global.auth.repository.UserRepository;
import com.global.auth.repository.projection.UserUuid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.UUID;

import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

@Slf4j
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        String[] roles = new String[user.getRoles().size()];
        return new User(user.getUsername(), user.getPassword(), createAuthorityList(user.getRoles().toArray(roles)));
    }

    public UUID loadUserUuid(String username) {
        UserUuid uuid = userRepository.findUuidByUsername(username);
        if (uuid == null) {
            throw new UsernameNotFoundException(username);
        }
        return uuid.getUuid();
    }

    @Transactional
    public void createUser(UserDataEvent dataEvent) {

        System.out.println("CREATE USER SUCCESSFUL");

        if (true) {
            return;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("john");
        userEntity.setPassword(new BCryptPasswordEncoder().encode("john"));
        userEntity.setRoles(Collections.singleton("ROLE_USER"));
        userRepository.save(userEntity);
    }

}
