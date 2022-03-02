package io.metadata.schoolregistration.model.dto;

import io.metadata.schoolregistration.model.dto.base.AuditBaseDTO;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO extends AuditBaseDTO {

    public StudentDTO(Long id, String firstName, String lastName){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    private Long id;

    @NotBlank(message = "First Name is mandatory")
    private String firstName;

    @NotBlank(message = "Last Name is mandatory")
    private String lastName;

    public Date createdAt;

    private Long createdBy;

    private Date updatedAt;

    private Long lastModifiedBy;


}
