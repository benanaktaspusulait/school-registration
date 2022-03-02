package io.metadata.schoolregistration.repository;

import io.metadata.schoolregistration.mapper.CourseRegistrationMapper;
import io.metadata.schoolregistration.model.CourseRegistration;
import io.metadata.schoolregistration.model.dto.CourseRegistrationDTO;
import io.metadata.schoolregistration.repository.base.BaseRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRegistrationRepository extends BaseRepository<CourseRegistration, CourseRegistrationDTO,
        Long, CourseRegistrationMapper>,QuerydslPredicateExecutor<CourseRegistration> {

    Integer countByStudentId(Long studentId);
    Integer countByCourseId(Long courseId);



}