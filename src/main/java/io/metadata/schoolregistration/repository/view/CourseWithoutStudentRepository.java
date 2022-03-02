package io.metadata.schoolregistration.repository.view;

import io.metadata.schoolregistration.model.view.CourseWithoutStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseWithoutStudentRepository extends JpaRepository<CourseWithoutStudent, Long> {
}
