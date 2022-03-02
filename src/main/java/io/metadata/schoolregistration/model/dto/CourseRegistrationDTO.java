package io.metadata.schoolregistration.model.dto;

import io.metadata.schoolregistration.model.dto.base.BaseDTO;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseRegistrationDTO extends BaseDTO {

    private StudentDTO student;

    @NotNull(message = "Student ID is mandatory")
    private Long studentId;

    private CourseDTO course;

    @NotNull(message = "Course ID is mandatory")
    private Long courseId;

    private Date registeredAt;

    private int grade;
}
