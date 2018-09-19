package com.global.shop.service;

import com.global.shop.model.learning.Section;

import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
public interface SectionService {

    List<Section> getSectionsByCourseName(String name);

    Section getSectionByNameAndId(String name, Long id);

}
