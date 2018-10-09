package com.global.shop.service;

import com.global.shop.model.learning.Section;
import com.global.shop.model.user.User;
import com.global.shop.model.wrapper.UserEntityDTO;

import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
public interface SectionService {

    List<Section> getSectionsByCourseName(String name);

    Section getSectionByCourseAndId(String name, Long id, User user);

    void startSection(UserEntityDTO wrapper);
}
