package com.global.education.mapper;

import com.global.education.controller.dto.Lesson;
import com.global.education.model.learning.LessonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * This class using for mapping between {@link LessonEntity} entity and DTO`s.
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface LessonMapper {

    LessonMapper INSTANCE = Mappers.getMapper(LessonMapper.class);

    List<Lesson> buildLessons(List<LessonEntity> lessonEntities);

    @Mappings({
            @Mapping(expression = "java(lessonEntity.getComments().stream().map(e -> new com.global.education.controller.dto.Comment(e.getAuthorId(), e.getLesson().getId(), e.getContent())).collect(java.util.stream.Collectors.toList()))", target = "comments")
    })
    Lesson buildLesson(LessonEntity lessonEntity);

}
