package com.global.education.mapper;

import com.global.education.controller.dto.Section;
import com.global.education.model.learning.SectionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * This class using for mapping between {@link SectionEntity} entity and DTO`s.
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface SectionMapper {

    SectionMapper INSTANCE = Mappers.getMapper(SectionMapper.class);

    List<Section> buildSections(List<SectionEntity> sectionEntities);

    @Mappings({
            @Mapping(expression = "java(sectionEntity.getCourse().getId())", target = "courseId")
    })
    Section buildSection(SectionEntity sectionEntity);

}