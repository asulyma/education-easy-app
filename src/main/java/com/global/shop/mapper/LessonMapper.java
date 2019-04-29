package com.global.shop.mapper;

import com.global.shop.model.learning.LessonEntity;
import com.global.shop.model.wrapper.LessonResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * This class using for mapping between {@link LessonEntity} entity and DTO`s.
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface LessonMapper {

    LessonMapper INSTANCE = Mappers.getMapper(LessonMapper.class);

    List<LessonResponse> buildLessons(List<LessonEntity> lessonEntities);

    @Mappings({
            @Mapping(expression = "java(lessonEntity.getSection().getId())", target = "sectionId")
    })
    LessonResponse buildLesson(LessonEntity lessonEntity);

}
