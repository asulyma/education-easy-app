package com.global.education.mapper;

import com.global.education.controller.dto.CommentResponse;
import com.global.education.model.learning.CommentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * This class using for mapping between {@link CommentEntity} entity and DTO`s.
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    List<CommentResponse> buildComments(List<CommentEntity> entities);

    @Mappings({
            @Mapping(expression = "java(entity.getLesson().getId())", target = "lessonId")
    })
    CommentResponse buildComment(CommentEntity entity);

}
