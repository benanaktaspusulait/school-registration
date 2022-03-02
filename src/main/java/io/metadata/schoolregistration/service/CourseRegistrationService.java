package io.metadata.schoolregistration.service;

import com.querydsl.core.BooleanBuilder;
import io.metadata.schoolregistration.exception.AppException;
import io.metadata.schoolregistration.exception.ResourceNotFoundException;
import io.metadata.schoolregistration.mapper.CourseRegistrationMapper;
import io.metadata.schoolregistration.model.CourseRegistration;
import io.metadata.schoolregistration.model.QCourseRegistration;
import io.metadata.schoolregistration.model.dto.CourseDTO;
import io.metadata.schoolregistration.model.dto.CourseRegistrationDTO;
import io.metadata.schoolregistration.model.dto.StudentDTO;
import io.metadata.schoolregistration.repository.CourseRegistrationRepository;
import io.metadata.schoolregistration.repository.CourseRepository;
import io.metadata.schoolregistration.repository.StudentRepository;
import io.metadata.schoolregistration.resolver.SchoolRegistrationPropertyResolver;
import io.metadata.schoolregistration.service.base.BaseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CourseRegistrationService extends BaseService<CourseRegistrationDTO, CourseRegistrationRepository, Long> {

    private CourseRegistrationRepository courseRegistrationRepository;
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;
    private SchoolRegistrationPropertyResolver propertyResolver;
    private CourseRegistrationMapper courseRegistrationMapper;

    public Page<CourseRegistrationDTO> findAll(Long studentId, Long courseId, Pageable pageable) throws ResourceNotFoundException {

        BooleanBuilder booleanBuilder = createBuilder(studentId,courseId);

        assert booleanBuilder.getValue() != null;
        Page<CourseRegistration> entities = courseRegistrationRepository.findAll(booleanBuilder.getValue(), pageable);
        Page<CourseRegistrationDTO> result = null;
        if (entities.hasContent()) {

            Set<Long> studentIds = entities.getContent().stream().map(CourseRegistration::getStudentId).collect(Collectors.toSet());
            Set<Long> courseIds = entities.getContent().stream().map(CourseRegistration::getCourseId).collect(Collectors.toSet());

            List<StudentDTO>  students = studentRepository.findAllStudentsWithId(studentIds);
            List<CourseDTO>  courses = courseRepository.findAllCoursesWithId(courseIds);

            List<CourseRegistrationDTO>  registrations = courseRegistrationMapper.toDto(entities.getContent());

            registrations.forEach(item -> {
                if (item.getStudentId() != null) {
                    item.setStudent(students.stream().filter(u -> u.getId().equals(item.getStudentId())).findAny().orElse(null));
                }
                if (item.getCourseId() != null) {
                    item.setCourse(courses.stream().filter(u -> u.getId().equals(item.getCourseId())).findAny().orElse(null));
                }
            });
            result = new PageImpl<>(courseRegistrationMapper.toDto(entities.getContent()), pageable, entities.getTotalElements());
        }
        if (result == null) {
            throw new ResourceNotFoundException("Records not found!");
        }
        return result;
    }

    public List<CourseRegistrationDTO> findAll(Long studentId, Long courseId) throws ResourceNotFoundException {

        BooleanBuilder booleanBuilder = createBuilder(studentId, courseId);

        assert booleanBuilder.getValue() != null;
        List<CourseRegistration> entities = (List<CourseRegistration>) courseRegistrationRepository.findAll(booleanBuilder.getValue());

        if (entities.isEmpty()) {
            throw new ResourceNotFoundException("Records not found!");
        }
        return courseRegistrationMapper.toDto(entities);
    }

    private BooleanBuilder createBuilder(Long studentId, Long courseId) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(QCourseRegistration.courseRegistration.id.isNotNull());

        if (studentId != null) {
            booleanBuilder.and(QCourseRegistration.courseRegistration.studentId.eq(studentId));
        }
        if (courseId != null) {
            booleanBuilder.and(QCourseRegistration.courseRegistration.courseId.eq(courseId));
        }
        return booleanBuilder;
    }

    public CourseRegistrationDTO register(CourseRegistrationDTO dto) throws AppException {

        Integer courseCountTakenByStudent = courseRegistrationRepository.countByStudentId(dto.getStudentId());
        Integer studentCountInCourse = courseRegistrationRepository.countByCourseId(dto.getCourseId());

        Integer maxCourseForStudent = propertyResolver.getLimit().getCount().getMaxCourseForStudent();
        Integer maxRegistrationInCourse = propertyResolver.getLimit().getCount().getMaxRegisterationInCourse();


        if(courseCountTakenByStudent > maxRegistrationInCourse) {
            throw new AppException(AppException.ErrorCodes.MAX_COURSE_FOR_STUDENT);
        }else if(studentCountInCourse > maxCourseForStudent) {
            throw new AppException(AppException.ErrorCodes.MAX_COURSE_FOR_STUDENT);
        }
        else {
            return super.save(dto);
        }

    }
}
