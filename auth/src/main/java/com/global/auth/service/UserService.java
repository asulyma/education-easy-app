package com.global.auth.service;

import com.global.auth.model.UserEntity;
import com.global.auth.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

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

        String[] rolesSize = new String[user.getRoles().size()];
        return new User(user.getUsername(), user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getRoles().toArray(rolesSize)));
    }

    @Transactional
    public UserEntity createUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("john");
        userEntity.setPassword(new BCryptPasswordEncoder().encode("john"));
        userEntity.setRoles(Collections.singleton("ROLE_USER"));
        userRepository.save(userEntity);
        return userEntity;
    }

}
