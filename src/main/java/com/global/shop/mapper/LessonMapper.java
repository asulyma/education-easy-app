package com.global.shop.mapper;

import com.global.shop.model.learning.LessonEntity;
import com.global.shop.model.wrapper.LessonResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

/**
 * This class using for mapping between {@link LessonEntity} entity and DTO`s.
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface LessonMapper {

    LessonResponse lessonToWrapper(LessonEntity lessonEntity);

    List<LessonResponse> buildLessons(List<LessonEntity> lessonEntities);

    @Mappings({
            @Mapping(expression = "java(lessonEntity.getAlreadyDone()" +
                    ".stream().map(com.global.shop.model.user.UserEntity::getId)" +
                    ".collect(java.util.stream.Collectors.toList()))", target = "alreadyDoneIds"),
            @Mapping(expression = "java(lessonEntity.getSectionEntity().getId())", target = "sectionId")
    })
    LessonResponse buildLesson(LessonEntity lessonEntity);

}
