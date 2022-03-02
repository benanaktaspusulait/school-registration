package io.metadata.schoolregistration.controller;

import io.metadata.schoolregistration.annotation.CustomRestController;
import io.metadata.schoolregistration.model.dto.CourseRegistrationDTO;
import io.metadata.schoolregistration.service.CourseRegistrationService;
import io.metadata.schoolregistration.service.ViewService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

import static io.metadata.schoolregistration.constant.Constants.*;

@Data
@Slf4j
@CustomRestController(API_PREFIX + REGISTRATIONS)
@AllArgsConstructor
public class CourseRegistrationController {

    private CourseRegistrationService courseRegistrationService;
    private ViewService viewService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CourseRegistrationDTO dto) {
        log.debug("CourseRegistration create by id : {}", dto);
        dto.setId(null);
        return new ResponseEntity<>(courseRegistrationService.register(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<CourseRegistrationDTO>> findAll(@PageableDefault @Parameter(hidden = true) Pageable pageable) {
        log.debug("All Course List : {}", pageable);
        return new ResponseEntity<>(courseRegistrationService.findAll(null, null, pageable), HttpStatus.OK);
    }

    @GetMapping("/student")
    public ResponseEntity<Page<CourseRegistrationDTO>> findAllCourses(@PageableDefault @Parameter(hidden = true) Pageable pageable,
                                                                      @RequestParam(value = "studentId", required = false) Long studentId
    ) {
        log.debug("Student's Course List : {}", pageable);
        return new ResponseEntity<>(courseRegistrationService.findAll(studentId, null, pageable), HttpStatus.OK);
    }

    @GetMapping(URL_LIST + "/student")
    public ResponseEntity<java.util.List<CourseRegistrationDTO>> findAllCoursesList(@RequestParam(value = "studentId", required = false) Long studentId
    ) {
        log.debug("Student's Course List : {}", studentId);

        return new ResponseEntity<>(courseRegistrationService.findAll(studentId, null), HttpStatus.OK);
    }

    @GetMapping("/course")
    public ResponseEntity<Page<CourseRegistrationDTO>> findAllStudents(@PageableDefault @Parameter(hidden = true) Pageable pageable,
                                                                       @RequestParam(value = "courseId", required = false) Long courseId) {
        log.debug("Course's students List : {}", pageable);
        return new ResponseEntity<>(courseRegistrationService.findAll(null, courseId, pageable), HttpStatus.OK);
    }

    @GetMapping(URL_LIST + "/course")
    public ResponseEntity<List<CourseRegistrationDTO>> findAllStudentsList(@RequestParam(value = "courseId", required = false) Long courseId) {
        log.debug("Course's students List : {}", courseId);
        return new ResponseEntity<>(courseRegistrationService.findAll(null, courseId), HttpStatus.OK);
    }

    @GetMapping(URL_LIST + COURSES_WITHOUT_STUDENT)
    public ResponseEntity<List<CourseRegistrationDTO>> findAllCourseWithoutStudents() {
        log.debug("Courses Without Student's  List : ");
        return new ResponseEntity(viewService.getAllCoursesWithoutStudents(), HttpStatus.OK);
    }

    @GetMapping(URL_LIST + STUDENTS_WITHOUT_COURSES)
    public ResponseEntity<List<CourseRegistrationDTO>> findAllStudentsWithoutCourses() {
        log.debug("Students Without Courses's  List : ");
        return new ResponseEntity(viewService.getAllStudentWithoutCourses(), HttpStatus.OK);
    }

}
