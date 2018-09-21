package com.global.shop.service.impl;

import com.global.shop.model.learning.Section;
import com.global.shop.repository.SectionRepository;
import com.global.shop.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Section> getSectionsByCourseName(String name) {
        return sectionRepository.findAllByCourseName(name);
    }

    @Override
    public Section getSectionByCourseAndId(String name, Long id) {
        return sectionRepository.findByCourseNameAndId(name, id);
    }
}
