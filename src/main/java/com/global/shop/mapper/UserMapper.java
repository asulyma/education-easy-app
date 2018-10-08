package com.global.shop.mapper;

import com.global.shop.model.user.User;
import com.global.shop.model.wrapper.UserViewWrapper;
import com.global.shop.model.wrapper.UserWrapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface UserMapper {

    List<UserWrapper> usersToListOfUserWrappers(List<User> users);

    @Mappings({
            @Mapping(expression = "java(user.getAllowedCourses()" +
                    ".stream().map(com.global.shop.model.learning.Course::getName)" +
                    ".collect(java.util.stream.Collectors.toList()))", target = "allowedCourses")
    })
    UserViewWrapper userToViewWrapper(User user);

}
