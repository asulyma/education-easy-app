package com.global.shop.service;

import com.global.shop.exception.BadRequestParametersRuntimeException;
import com.global.shop.exception.NotAllowedRuntimeException;
import com.global.shop.exception.NotFoundRuntimeException;
import com.global.shop.model.learning.SectionEntity;
import com.global.shop.model.user.UserEntity;
import com.global.shop.repository.SectionRepository;
import com.global.shop.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Service
@Slf4j
public class SectionService {

    private final SectionRepository sectionRepository;
    private final UserRepository userRepository;

    @Autowired
    public SectionService(SectionRepository sectionRepository, UserRepository userRepository) {
        this.sectionRepository = sectionRepository;
        this.userRepository = userRepository;
    }

    public List<SectionEntity> getSectionsByCourseName(String name) {
        return sectionRepository.findAllByCourseName(name);
    }

    @Transactional
    public void startSection(Long sectionId, Long userId) {

        Optional<SectionEntity> optionalSection = sectionRepository.findById(sectionId);
        Optional<UserEntity> optionalUser = userRepository.findById(userId);

        SectionEntity sectionEntity = optionalSection.orElseThrow(() ->
                new NotFoundRuntimeException("No available sectionEntity: " + sectionId));
        UserEntity userEntity = optionalUser.orElseThrow(() ->
                new NotFoundRuntimeException("No available userEntity: " + userId));

        if (userEntity.getAllowedSections().contains(sectionEntity)) {
            throw new BadRequestParametersRuntimeException("SectionEntity: " + sectionId
                    + " already allowed for userEntity: " + userId);
        }

        userEntity.getAllowedSections().add(sectionEntity);
        log.info("Given access for userEntity: '" + userEntity.getLogin() + "' to sectionEntity with id: "
                + sectionEntity.getId());
    }

    public SectionEntity getSectionByCourseAndId(String name, Long id, UserEntity userEntity) {
        SectionEntity sectionEntity = sectionRepository.findByCourseNameAndId(name, id);

        if (userEntity.getAllowedSections().contains(sectionEntity)) {
            return sectionEntity;
        } else {
            throw new NotAllowedRuntimeException(
                    "SectionEntity with id: " + sectionEntity.getId() + " are not allowed for userEntity: "
                            + userEntity.getLogin());
        }
    }

}
