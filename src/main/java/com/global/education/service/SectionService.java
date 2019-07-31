package com.global.education.service;

import com.global.education.controller.dto.Section;
import com.global.education.model.learning.SectionEntity;
import com.global.education.repository.SectionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class for CRUD operations with {@link SectionEntity} class.
 */
@Slf4j
@Service
public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private CourseService courseService;

    public List<SectionEntity> getSections(String name) {
        return sectionRepository.findAllByCourseName(name);
    }

    public SectionEntity getSectionByCourseAndId(String name, Long id) {
        return sectionRepository.findByCourseNameAndId(name, id);
    }

    @Transactional
    public SectionEntity createSection(Section section) {
        SectionEntity entity = new SectionEntity();
        entity.setCourse(courseService.getCourseById(section.getCourseId()))
              .setDescription(section.getDescription())
              .setTitle(section.getTitle());

        log.info("New section with title: " + section.getTitle() + " has been created.");
        return entity;
    }

    public void deleteSection(Long id) {
        sectionRepository.deleteById(id);
        log.info("Section with id: " + id + " has been created.");
    }

}
