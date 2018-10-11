package com.global.shop.mapper;

import com.global.shop.model.learning.Course;
import com.global.shop.model.wrapper.CourseViewWrapper;
import com.global.shop.model.wrapper.CourseWrapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

/**
 * This class using for mapping between {@link Course} entity and DTO`s.
 *
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface CourseMapper {

    @Mapping(expression = "java(course.getAllowedUsers()" +
            ".stream().map(com.global.shop.model.user.User::getId)" +
            ".collect(java.util.stream.Collectors.toList()))", target = "allowedUsers")
    CourseViewWrapper courseToViewWrapper(Course course);

    CourseWrapper courseToWrapper(Course course);

    List<CourseWrapper> coursesToListOfWrappers(List<Course> courses);

}
