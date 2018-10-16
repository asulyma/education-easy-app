package com.global.shop.mapper;

import com.global.shop.model.learning.Lesson;
import com.global.shop.model.wrapper.LessonViewWrapper;
import com.global.shop.model.wrapper.LessonWrapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

/**
 * This class using for mapping between {@link Lesson} entity and DTO`s.
 *
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface LessonMapper {

    @Mapping(expression = "java(lesson.getComment().stream().count())", target = "countOfComments")
    LessonWrapper lessonToWrapper(Lesson lesson);

    List<LessonWrapper> lessonsToListOfWrappers(List<Lesson> lessons);

    @Mappings({
            @Mapping(expression = "java(lesson.getAlreadyDone()" +
                    ".stream().map(com.global.shop.model.user.User::getId)" +
                    ".collect(java.util.stream.Collectors.toList()))", target = "alreadyDoneIds"),
            @Mapping(expression = "java(lesson.getSection().getId())", target = "sectionId")
    })
    LessonViewWrapper lessonToViewWrapper(Lesson lesson);

}
