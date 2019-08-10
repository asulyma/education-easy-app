package com.global.education.mapper;

import com.global.education.controller.dto.Course;
import com.global.education.model.learning.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * This class using for mapping between {@link CourseEntity} entity and DTO`s.
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    List<Course> buildCourses(List<CourseEntity> courseEntities);

    Course buildCourse(CourseEntity courseEntity);

}
