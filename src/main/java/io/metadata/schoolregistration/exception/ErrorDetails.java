package io.metadata.schoolregistration.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorDetails {

    private Date errorDate;
    private String message;
    private String details;
}
