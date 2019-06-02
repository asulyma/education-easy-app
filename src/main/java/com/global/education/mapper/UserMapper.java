package com.global.education.mapper;

import com.global.education.model.user.UserEntity;
import com.global.education.model.wrapper.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * This class using for mapping between {@link UserEntity} entity and DTO`s.
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    List<UserResponse> buildUsersResponse(List<UserEntity> userEntities);

    @Mappings({
            @Mapping(expression = "java(userEntity.getAllowedCourses()" +
                    ".stream().map(com.global.education.model.learning.CourseEntity::getName)" +
                    ".collect(java.util.stream.Collectors.toList()))", target = "allowedCourses")
    })
    UserResponse buildFullUser(UserEntity userEntity);

}
