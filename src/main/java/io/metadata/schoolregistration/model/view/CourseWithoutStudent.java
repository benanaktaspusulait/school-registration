package io.metadata.schoolregistration.model.view;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Immutable
@Table(name = "COURSE_WITHOUT_STUDENTS")
public class CourseWithoutStudent {

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", unique = true)
    private String name;
}
