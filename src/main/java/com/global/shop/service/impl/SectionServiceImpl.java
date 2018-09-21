package com.global.shop.service.impl;

import com.global.shop.model.learning.Section;
import com.global.shop.model.user.User;
import com.global.shop.model.wrapper.SectionWrapper;
import com.global.shop.repository.SectionRepository;
import com.global.shop.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Service
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;

    @Autowired
    public SectionServiceImpl(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }


    @Override
    public List<SectionWrapper> getSectionsByCourseName(String name) {
        List<Section> sections = sectionRepository.findAllByCourseName(name);

        return buildSectionWrappers(sections);
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

    @Override
    public Section getSectionByCourseAndId(String name, Long id) {
        return sectionRepository.findByCourseNameAndId(name, id);
    }
}
