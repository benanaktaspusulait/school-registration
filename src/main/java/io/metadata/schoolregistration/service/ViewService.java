package io.metadata.schoolregistration.service;

import io.metadata.schoolregistration.model.view.CourseWithoutStudent;
import io.metadata.schoolregistration.model.view.StudentWithoutCourse;
import io.metadata.schoolregistration.repository.view.CourseWithoutStudentRepository;
import io.metadata.schoolregistration.repository.view.StudentWithoutCourseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ViewService {

    private CourseWithoutStudentRepository courseWithoutStudentRepository;
    private StudentWithoutCourseRepository studentWithoutCourseRepository;

    public List<StudentWithoutCourse> getAllStudentWithoutCourses() {
        return studentWithoutCourseRepository.findAll();
    }

    public List<CourseWithoutStudent> getAllCoursesWithoutStudents() {
        return courseWithoutStudentRepository.findAll();
    }

}
