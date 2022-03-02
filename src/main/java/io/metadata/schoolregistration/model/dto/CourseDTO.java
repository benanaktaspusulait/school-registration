package io.metadata.schoolregistration.model.dto;

import io.metadata.schoolregistration.model.dto.base.BaseDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class CourseDTO extends BaseDTO {

    public CourseDTO(Long id, String name){
        this.id = id;
        this.name = name;
    }

    @NotBlank(message = "Course Name is mandatory")
    private String name;

    public Date createdAt;

    private Long createdBy;

    private Date updatedAt;

    private Long lastModifiedBy;

}
