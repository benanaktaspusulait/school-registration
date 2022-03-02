package io.metadata.schoolregistration.service;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    // private StudentService studentService;
    // private Faker faker;
    // private CourseService courseService;
    //private SchoolRegistrationPropertyResolver propertyResolver;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        seedData();
    }

    private void seedData() {

      /*  Integer maxStudentCount = propertyResolver.getLimit().getCount().getStudent();

        for(int i = 0; i < maxStudentCount; i++){
            studentService.save(StudentDTO.builder()
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .createdAt(new Date())
                    .build());
        }
        for(int i = 0; i < 10; i++){
            courseService.save(CourseDTO.builder()
                    .createdAt(new Date())
                    .name(faker.)
                    .build());
        }
*/
    }
}
