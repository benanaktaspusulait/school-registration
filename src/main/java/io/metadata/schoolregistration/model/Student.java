package io.metadata.schoolregistration.model;

import io.metadata.schoolregistration.model.audit.AuditBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "STUDENTS",uniqueConstraints = { @UniqueConstraint(columnNames = { "FIRSTNAME", "LASTNAME" }) })
public class Student extends AuditBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "FIRSTNAME", nullable = false)
    private String firstName;

    @Column(name = "LASTNAME", nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "student")
    private Set<CourseRegistration> registrations = new HashSet<>();

}
