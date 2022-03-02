package io.metadata.schoolregistration.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class AppException extends RuntimeException {

    Integer messageId;

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(Integer messageId) {
        this.messageId = messageId;
    }

    public interface ErrorCodes{

        Integer GENERAL = 100;
        Integer MAX_COURSE_FOR_STUDENT = 101;
        Integer MAX_REGISTRATION_IN_COURSE = 102;



    }

}

