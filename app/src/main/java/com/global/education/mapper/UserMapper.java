package com.global.education.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import com.global.education.controller.dto.User;
import com.global.education.model.UserDataEntity;


/**
 * This class using for mapping between {@link com.global.education.model.UserDataEntity} entity and DTO`s.
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	@Mappings({
			@Mapping(expression = "java(userDataEntity.getRank().getDescription())", target = "rank")
	})
	User buildUser(UserDataEntity userDataEntity);

}
