package com.global.education.service;

import com.global.education.controller.dto.SpecificationRequest;
import com.global.education.model.learning.CourseEntity;
import com.global.education.model.user.Progress;
import com.global.education.model.user.Role;
import com.global.education.model.user.UserEntity;
import com.global.education.repository.UserRepository;
import com.global.education.service.specification.SpecificationCriteria;
import com.global.education.service.specification.UserSpecificationFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.global.education.mapper.SpecificationMapper.INSTANCE;
import static com.global.education.util.ProjectUtils.checkAndGetOptional;

/**
 * Service for CRUD operations on UserEntity.
 */
@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSpecificationFactory specificationBuilder;

    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public UserEntity createUser(UserEntity userEntity) {
        return userRepository.saveAndFlush(userEntity);
    }

    public List<UserEntity> getUserByRole(Role role) {
        return userRepository.findByRole(role);
    }

    public UserEntity getUserById(Long userId) {
        return checkAndGetOptional(userRepository.findById(userId), userId);
    }

    public List<UserEntity> findAll(SpecificationRequest request) {
        SpecificationCriteria criteria = INSTANCE.buildSpecificationCriteria(request);
        Specification<UserEntity> specification = specificationBuilder.build(criteria);
        return userRepository.findAll(specification);
    }

    @Transactional
    public Long addCourseForUser(UserEntity userEntity, CourseEntity courseEntity) {
        userEntity.getAllowedCourses().add(courseEntity);
        userEntity.setProgress(new Progress(courseEntity.getName(), 0L));
        log.info("Given access for userEntity: '" + userEntity.getLogin() + "' to courseEntity with id: "
                + courseEntity.getId());
        return userEntity.getId();
    }

}
