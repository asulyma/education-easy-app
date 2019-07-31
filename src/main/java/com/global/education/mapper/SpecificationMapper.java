package com.global.education.mapper;

import com.global.education.controller.dto.SpecificationRequest;
import com.global.education.service.specification.SpecificationCriteria;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface SpecificationMapper {

    SpecificationMapper INSTANCE = Mappers.getMapper(SpecificationMapper.class);

    SpecificationCriteria buildSpecificationCriteria(SpecificationRequest request);

}
