package io.metadata.schoolregistration.resolver;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.Serializable;

@Data
@Configuration
@Primary
@ConfigurationProperties(prefix = "properties")
public class SchoolRegistrationPropertyResolver implements Serializable {

    private Limit limit;

    @Data
    public static class Limit implements Serializable {
        private Count count;
    }
    @Data
    public static class Count implements Serializable {
        private Integer maxCourseForStudent;
        private Integer maxRegisterationInCourse;
    }
}
