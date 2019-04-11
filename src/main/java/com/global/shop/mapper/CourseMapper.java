package com.global.shop.mapper;

import com.global.shop.model.learning.CourseEntity;
import com.global.shop.model.wrapper.CourseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

/**
 * This class using for mapping between {@link CourseEntity} entity and DTO`s.
 *
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface CourseMapper {

    List<CourseResponse> buildCourses(List<CourseEntity> courseEntities);

    CourseResponse buildCourse(CourseEntity courseEntity);

}
