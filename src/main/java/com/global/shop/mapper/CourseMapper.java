package com.global.shop.mapper;

import com.global.shop.model.learning.Course;
import com.global.shop.model.wrapper.CourseWrapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface CourseMapper {

    List<CourseWrapper> courseToListOfWrappers(List<Course> wrappers);

}
