package io.metadata.schoolregistration.mapper;

import io.metadata.schoolregistration.mapper.base.BaseEntityMapper;
import io.metadata.schoolregistration.model.Course;
import io.metadata.schoolregistration.model.dto.CourseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CourseMapper extends BaseEntityMapper<CourseDTO, Course> {

}
