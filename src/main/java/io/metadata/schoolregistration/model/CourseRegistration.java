package io.metadata.schoolregistration.model;

import io.metadata.schoolregistration.model.audit.AuditBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "COURSE_REGISTRATION")
public class CourseRegistration extends AuditBase implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDENT_ID", insertable = false, updatable = false,
                                foreignKey = @ForeignKey(name = "COURSE_REGISTRATION_STUDENT"))
    private Student student;

    @Column(name = "STUDENT_ID", nullable = false)
    private Long studentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COURSE_ID", insertable = false, updatable = false,
                                foreignKey = @ForeignKey(name = "COURSE_REGISTRATION_COURSE"))
    private Course course;

    @Column(name = "COURSE_ID", nullable = false)
    private Long courseId;

    @Column(name = "registered_at")
    private Date registeredAt;

    @Column(name = "grade")
    private int grade;
}