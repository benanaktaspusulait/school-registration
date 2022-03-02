package io.metadata.schoolregistration.model.dto.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class AuditBaseDTO extends BaseDTO {

    public Date createdAt;
    private Long createdBy = 1L;
    private Date updatedAt;
    private Long lastModifiedBy;
}
