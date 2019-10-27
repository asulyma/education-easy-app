package com.global.education.mapper;

import com.global.education.controller.dto.Course;
import com.global.education.controller.dto.SharedCourse;
import com.global.education.model.learning.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class using for mapping between {@link CourseEntity} entity and DTO`s.
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    default List<Course> buildCourses(List<CourseEntity> courseEntities) {
        return courseEntities.stream()
                             .map(this::buildCourse)
                             .collect(Collectors.toList());
    }

    Course buildCourse(CourseEntity courseEntity);

    SharedCourse buildSharedCourse(CourseEntity courseEntity);

    CourseEntity buildEntity(Course course);

}
