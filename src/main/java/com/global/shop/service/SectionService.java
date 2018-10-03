package com.global.shop.service;

import com.global.shop.model.learning.Section;
import com.global.shop.model.wrapper.NotificationWrapper;
import com.global.shop.model.wrapper.SectionWrapper;

import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
public interface SectionService {

    List<SectionWrapper> getSectionsByCourseName(String name);

    Section getSectionByCourseAndId(String name, Long id);

    void startSection(NotificationWrapper wrapper);
}
