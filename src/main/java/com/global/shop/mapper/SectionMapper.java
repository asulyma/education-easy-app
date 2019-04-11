package com.global.shop.mapper;

import com.global.shop.model.learning.SectionEntity;
import com.global.shop.model.wrapper.SectionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

/**
 * This class using for mapping between {@link SectionEntity} entity and DTO`s.
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface SectionMapper {

    List<SectionResponse> buildSections(List<SectionEntity> sectionEntities);

    @Mappings({
            @Mapping(expression = "java(sectionEntity.getCourse().getId())", target = "courseId")
    })
    SectionResponse buildSection(SectionEntity sectionEntity);

}
