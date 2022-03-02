package io.metadata.schoolregistration.service;

import io.metadata.schoolregistration.model.dto.CourseDTO;
import io.metadata.schoolregistration.repository.CourseRepository;
import io.metadata.schoolregistration.service.base.BaseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class CourseService extends BaseService<CourseDTO, CourseRepository, Long> {
}
