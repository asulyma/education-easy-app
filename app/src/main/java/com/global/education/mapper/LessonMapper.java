package com.global.education.mapper;

import com.global.education.controller.dto.Lesson;
import com.global.education.controller.dto.SharedLesson;
import com.global.education.model.learning.LessonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class using for mapping between {@link LessonEntity} entity and DTO`s.
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface LessonMapper {

    LessonMapper INSTANCE = Mappers.getMapper(LessonMapper.class);

    default List<Lesson> buildLessons(List<LessonEntity> lessonEntities) {
        return lessonEntities.stream()
                             .map(this::buildLesson)
                             .collect(Collectors.toList());
    }

    @Mappings({
            @Mapping(expression = "java(lessonEntity.getCourse().getId())", target = "courseId")
    })
    Lesson buildLesson(LessonEntity lessonEntity);

    @Mappings({
            @Mapping(expression = "java(lessonEntity.getCourse().getId())", target = "courseId"),
            @Mapping(expression = "java(lessonEntity.getComments().stream()"
                    + ".map(e -> new com.global.education.controller.dto.Comment(e.getAuthorUuid(), e.getLesson().getId(), null, e.getContent()))"
                    + ".collect(java.util.stream.Collectors.toList()))", target = "comments")
    })
    SharedLesson buildSharedLesson(LessonEntity lessonEntity);

}
