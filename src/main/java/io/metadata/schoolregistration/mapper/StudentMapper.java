package io.metadata.schoolregistration.mapper;

import io.metadata.schoolregistration.mapper.base.BaseEntityMapper;
import io.metadata.schoolregistration.model.Student;
import io.metadata.schoolregistration.model.dto.StudentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface StudentMapper extends BaseEntityMapper<StudentDTO, Student> {

}