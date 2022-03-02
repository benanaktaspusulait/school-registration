package io.metadata.schoolregistration.repository.view;

import io.metadata.schoolregistration.model.view.StudentWithoutCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentWithoutCourseRepository extends JpaRepository<StudentWithoutCourse, Long> {
}
