package com.global.education.mapper;

import com.global.education.controller.dto.User;
import com.global.education.model.user.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * This class using for mapping between {@link UserEntity} entity and DTO`s.
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    List<User> buildUsersResponse(List<UserEntity> userEntities);

    @Mappings({
            @Mapping(expression = "java(userEntity.getAllowedCourses()" +
                    ".stream().map(com.global.education.model.learning.CourseEntity::getId)" +
                    ".collect(java.util.stream.Collectors.toList()))", target = "allowedCourses"),
            @Mapping(expression = "java(userEntity.getAlreadyDoneLessons()" +
                    ".stream().map(com.global.education.model.learning.LessonEntity::getId)" +
                    ".collect(java.util.stream.Collectors.toList()))", target = "alreadyDoneLessons")
    })
    User buildFullUser(UserEntity userEntity);

}
