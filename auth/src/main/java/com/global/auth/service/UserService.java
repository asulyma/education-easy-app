package com.global.auth.service;

import com.global.auth.model.DataType;
import com.global.auth.model.UserEntity;
import com.global.auth.model.UserProvider;
import com.global.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void addAllowedCourse(Long userId, Long courseId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(NullPointerException::new);
        user.getUserData().get(DataType.ALLOWED_COURSES).add(courseId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        UserProvider userProvider = new UserProvider(user.getUsername(), user.getPassword(), user.getRoles());
        userProvider.setUserEntity(user);
        return userProvider;
    }

}
