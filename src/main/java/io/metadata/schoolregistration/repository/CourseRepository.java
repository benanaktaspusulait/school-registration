package io.metadata.schoolregistration.repository;

import io.metadata.schoolregistration.mapper.CourseMapper;
import io.metadata.schoolregistration.model.Course;
import io.metadata.schoolregistration.model.dto.CourseDTO;
import io.metadata.schoolregistration.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface CourseRepository extends BaseRepository<Course, CourseDTO, Long, CourseMapper>,
        QuerydslPredicateExecutor<Course> {

    @Query("select new io.metadata.schoolregistration.model.dto.CourseDTO(u.id,u.name) from Course u where u.id in (?1)")
    List<CourseDTO> findAllCoursesWithId(Set<Long> courseIds);
}