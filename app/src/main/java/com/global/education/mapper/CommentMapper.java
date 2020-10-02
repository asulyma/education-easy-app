package com.global.education.mapper;

import java.util.List;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import com.global.education.controller.dto.SharedComment;
import com.global.education.model.learning.CommentEntity;


/**
 * This class using for mapping between {@link CommentEntity} entity and DTO`s.
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface CommentMapper {

	CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

	List<SharedComment> buildComments(List<CommentEntity> entities);

	@Mappings({
			@Mapping(expression = "java(entity.getLesson().getId())", target = "lessonId"),
			@Mapping(expression = "java(entity.getLesson().getCourse().getId())", target = "courseId")
	})
	SharedComment buildComment(CommentEntity entity);

}
