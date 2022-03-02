package io.metadata.schoolregistration.controller;

import io.metadata.schoolregistration.annotation.CustomRestController;
import io.metadata.schoolregistration.constant.Constants;
import io.metadata.schoolregistration.model.dto.CourseDTO;
import io.metadata.schoolregistration.service.CourseService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;


@Data
@Slf4j
@CustomRestController(Constants.API_PREFIX + "/courses")
@RequiredArgsConstructor
public class CourseController {

    private CourseService courseService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CourseDTO dto) {
        log.debug("Course create by id : {}", dto);
        dto.setId(null);
        return new ResponseEntity<>(courseService.save(dto), HttpStatus.OK);
    }



}
