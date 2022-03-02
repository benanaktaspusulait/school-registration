package io.metadata.schoolregistration.service;

import com.querydsl.core.BooleanBuilder;
import io.metadata.schoolregistration.exception.ResourceNotFoundException;
import io.metadata.schoolregistration.mapper.StudentMapper;
import io.metadata.schoolregistration.model.QStudent;
import io.metadata.schoolregistration.model.Student;
import io.metadata.schoolregistration.model.dto.StudentDTO;
import io.metadata.schoolregistration.repository.StudentRepository;
import io.metadata.schoolregistration.service.base.BaseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class StudentService extends BaseService<StudentDTO, StudentRepository, Long> {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public Page<StudentDTO> findAll(String firstName, String lastName, Pageable pageable) throws ResourceNotFoundException {

        BooleanBuilder booleanBuilder = createBuilder(firstName, lastName);

        assert booleanBuilder.getValue() != null;
        Page<Student> entities = studentRepository.findAll(booleanBuilder.getValue(), pageable);
        Page<StudentDTO> result = null;
        if (entities.hasContent()) {
            result = new PageImpl<>(studentMapper.toDto(entities.getContent()), pageable, entities.getTotalElements());
        }
        if (result == null) {
            throw new ResourceNotFoundException("Records not found!");
        }
        return result;
    }

    public List<StudentDTO> findAll(String firstName, String lastName) throws ResourceNotFoundException {

        BooleanBuilder booleanBuilder = createBuilder(firstName, lastName);

        assert booleanBuilder.getValue() != null;
        List<Student> entities = (List<Student>) studentRepository.findAll(booleanBuilder.getValue());

        if (entities.isEmpty()) {
            throw new ResourceNotFoundException("Records not found!");
        }
        return studentMapper.toDto(entities);
    }

    private BooleanBuilder createBuilder(String firstName, String lastName) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(QStudent.student.id.isNotNull());

        if (firstName != null) {
            booleanBuilder.and(QStudent.student.firstName.contains(firstName));
        }
        if (lastName != null) {
            booleanBuilder.and(QStudent.student.lastName.contains(lastName));
        }
        return booleanBuilder;
    }


}
