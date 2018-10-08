package com.global.shop.mapper;

import com.global.shop.model.learning.Section;
import com.global.shop.model.wrapper.SectionViewWrapper;
import com.global.shop.model.wrapper.SectionWrapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface SectionMapper {

    @Mapping(expression = "java(section.getAllowedUsers()" +
            ".stream().map(com.global.shop.model.user.User::getId)" +
            ".collect(java.util.stream.Collectors.toList()))", target = "allowedUsers")
    SectionWrapper sectionToWrapper(Section section);

    List<SectionWrapper> sectionsToListOfWrapper(List<Section> sections);

    @Mapping(expression = "java(section.getAllowedUsers()" +
            ".stream().map(com.global.shop.model.user.User::getId)" +
            ".collect(java.util.stream.Collectors.toList()))", target = "allowedUsers")
    SectionViewWrapper sectionToViewWrapper(Section section);

}
