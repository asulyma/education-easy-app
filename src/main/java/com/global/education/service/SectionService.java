package com.global.education.service;

import com.global.education.exception.BadRequestParametersRuntimeException;
import com.global.education.exception.NotAllowedRuntimeException;
import com.global.education.exception.NotFoundRuntimeException;
import com.global.education.model.learning.SectionEntity;
import com.global.education.model.user.UserEntity;
import com.global.education.repository.SectionRepository;
import com.global.education.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

        SectionEntity sectionEntity = sectionRepository.findById(sectionId).orElseThrow(NotFoundRuntimeException::new);
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(NotFoundRuntimeException::new);

        if (userEntity.getAllowedSections().contains(sectionEntity)) {
            throw new BadRequestParametersRuntimeException("SectionEntity: " + sectionId
                    + " has been started previously for user: " + userId);
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
