package io.metadata.schoolregistration.controller;

import io.metadata.schoolregistration.annotation.CustomRestController;
import io.metadata.schoolregistration.constant.Constants;
import io.metadata.schoolregistration.exception.AppException;
import io.metadata.schoolregistration.model.dto.StudentDTO;
import io.metadata.schoolregistration.service.StudentService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static io.metadata.schoolregistration.constant.Constants.URL_LIST;

@Data
@Slf4j
@CustomRestController(Constants.API_PREFIX + "/students")
@AllArgsConstructor
public class StudentController {

    private StudentService studentService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid StudentDTO dto) {
        log.debug("Student create by id : {}", dto);
        dto.setId(null);
        return new ResponseEntity<>(studentService.save(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<StudentDTO>> findAll(@PageableDefault @Parameter(hidden = true) Pageable pageable,
                                                    @RequestParam(value = "firstName", required = false) String firstName,
                                                    @RequestParam(value = "lastName", required = false) String lastName) {
        log.debug("Student List : {}, {},{}", pageable, firstName, lastName);
        return new ResponseEntity<>(studentService.findAll(firstName, lastName, pageable), HttpStatus.OK);
    }

    @GetMapping(URL_LIST)
    public ResponseEntity<List<StudentDTO>> findAll(@RequestParam(value = "firstName", required = false) String firstName,
                                                    @RequestParam(value = "lastName", required = false) String lastName) {
        log.debug("Student List : {},{}", firstName, lastName);
        return new ResponseEntity<>(studentService.findAll(firstName, lastName), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)throws AppException {
        log.debug("REST funds to delete student : {}", id);
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


}
