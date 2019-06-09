package com.global.education.service;

import com.global.education.model.learning.SectionEntity;
import com.global.education.repository.SectionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for CRUD operations with {@link SectionEntity} class.
 */
@Slf4j
@Service
public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    public List<SectionEntity> getSections(String name) {
        return sectionRepository.findAllByCourseName(name);
    }

    public SectionEntity getSectionByCourseAndId(String name, Long id) {
        return sectionRepository.findByCourseNameAndId(name, id);
    }

    //TODO add CRUD functionality for sections

}
