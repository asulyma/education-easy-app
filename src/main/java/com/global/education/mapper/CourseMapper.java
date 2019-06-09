package com.global.education.mapper;

import com.global.education.model.learning.CourseEntity;
import com.global.education.model.wrapper.CourseResponse;
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

    List<CourseResponse> buildCourses(List<CourseEntity> courseEntities);

    CourseResponse buildCourse(CourseEntity courseEntity);

}
