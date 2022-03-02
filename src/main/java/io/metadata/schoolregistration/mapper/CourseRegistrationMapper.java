package io.metadata.schoolregistration.mapper;

import io.metadata.schoolregistration.mapper.base.BaseEntityMapper;
import io.metadata.schoolregistration.model.CourseRegistration;
import io.metadata.schoolregistration.model.dto.CourseRegistrationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CourseRegistrationMapper extends BaseEntityMapper<CourseRegistrationDTO, CourseRegistration> {

}