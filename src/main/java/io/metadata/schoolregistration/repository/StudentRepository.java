package io.metadata.schoolregistration.repository;

import io.metadata.schoolregistration.mapper.StudentMapper;
import io.metadata.schoolregistration.model.Student;
import io.metadata.schoolregistration.model.dto.StudentDTO;
import io.metadata.schoolregistration.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface StudentRepository extends BaseRepository<Student, StudentDTO, Long, StudentMapper>,
                                                                QuerydslPredicateExecutor<Student> {
    @Query("select new io.metadata.schoolregistration.model.dto.StudentDTO(u.id,u.firstName, u.lastName) from Student u where u.id in (?1)"   )
    List<StudentDTO> findAllStudentsWithId(Set<Long> studentIds);



}
