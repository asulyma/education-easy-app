package com.global.shop.service.impl;

import com.global.shop.exception.NotAllowedRuntimeException;
import com.global.shop.exception.NotFoundRuntimeException;
import com.global.shop.model.learning.Section;
import com.global.shop.model.user.User;
import com.global.shop.model.wrapper.UserEntityDTO;
import com.global.shop.repository.SectionRepository;
import com.global.shop.repository.UserRepository;
import com.global.shop.service.SectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Service
@Slf4j
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;
    private final UserRepository userRepository;

    @Autowired
    public SectionServiceImpl(SectionRepository sectionRepository, UserRepository userRepository) {
        this.sectionRepository = sectionRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<Section> getSectionsByCourseName(String name) {
        return sectionRepository.findAllByCourseName(name);
    }

    @Override
    public void startSection(UserEntityDTO wrapper) {

        Optional<Section> optionalSection = sectionRepository.findById(wrapper.getEntityId());
        Optional<User> optionalUser = userRepository.findById(wrapper.getUserId());

        Section section = optionalSection.orElseThrow(() ->
                new NotFoundRuntimeException("No available section: " + wrapper.getEntityId()));
        User user = optionalUser.orElseThrow(() ->
                new NotFoundRuntimeException("No available user: " + wrapper.getUserId()));

        user.getAllowedSections().add(section);
        userRepository.saveAndFlush(user);
        log.info("Given access for user: '" + user.getLogin() + "' to section with id: " + section.getId());
    }

    @Override
    public Section getSectionByCourseAndId(String name, Long id, User user) {

        Section section = sectionRepository.findByCourseNameAndId(name, id);

        if (section.getAllowedUsers().contains(user)) {
            return section;
        } else {
            throw new NotAllowedRuntimeException("Section with id: " + section.getId() + " are not allowed for user: " + user.getLogin());
        }
    }

}
