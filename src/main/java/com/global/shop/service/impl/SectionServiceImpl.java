package com.global.shop.service.impl;

import com.global.shop.exception.NotFoundRuntimeException;
import com.global.shop.model.learning.Section;
import com.global.shop.model.notification.Notification;
import com.global.shop.model.user.User;
import com.global.shop.model.wrapper.NotificationWrapper;
import com.global.shop.model.wrapper.SectionWrapper;
import com.global.shop.repository.SectionRepository;
import com.global.shop.repository.UserRepository;
import com.global.shop.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Service
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;
    private final UserRepository userRepository;

    @Autowired
    public SectionServiceImpl(SectionRepository sectionRepository, UserRepository userRepository) {
        this.sectionRepository = sectionRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<SectionWrapper> getSectionsByCourseName(String name) {
        List<Section> sections = sectionRepository.findAllByCourseName(name);

        return buildSectionWrappers(sections);
    }

    @Override
    public void startSection(Notification notification) {

        Optional<Section> optionalSection = sectionRepository.findById(notification.getIdOfEntity());
        Optional<User> optionalUser = userRepository.findById(notification.getRecipientId());

        Section section = optionalSection.orElseThrow(() -> new NotFoundRuntimeException("No available section: " + notification.getIdOfEntity()));
        User user = optionalUser.orElseThrow(() -> new NotFoundRuntimeException("No available user: " + notification.getRecipientId()));

        user.getAllowedSections().add(section);
        userRepository.saveAndFlush(user);

    }

    @Override
    public Section getSectionByCourseAndId(String name, Long id) {
        //TODO filter by allowed users
        return sectionRepository.findByCourseNameAndId(name, id);
    }

    private List<SectionWrapper> buildSectionWrappers(List<Section> sections) {

        List<SectionWrapper> wrappers = new ArrayList<>();
        sections.forEach(section -> {
            SectionWrapper wrapper = new SectionWrapper();
            wrapper.setId(section.getId());
            wrapper.setTitle(section.getTitle());
            wrapper.setAllowedUsers(section.getAllowedUsers().stream().map(User::getId).collect(Collectors.toList()));
            wrappers.add(wrapper);
        });
        return wrappers;
    }
}
