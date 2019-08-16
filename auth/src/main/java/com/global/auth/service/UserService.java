package com.global.auth.service;

import com.global.auth.model.DataType;
import com.global.auth.model.UserEntity;
import com.global.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void addAllowedCourse(Long userId, Long courseId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(NullPointerException::new);
        user.getUserData().get(DataType.ALLOWED_COURSES).add(courseId);
    }

}
