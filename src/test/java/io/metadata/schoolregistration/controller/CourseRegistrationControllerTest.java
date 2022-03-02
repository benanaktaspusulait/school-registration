package io.metadata.schoolregistration.controller;

import io.metadata.schoolregistration.model.dto.CourseRegistrationDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;
import java.util.List;

import static io.metadata.schoolregistration.constant.Constants.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
public class CourseRegistrationControllerTest extends AbstractControllerTest {

    private final String URI = API_PREFIX + URL_REGISTRATIONS;


    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void shouldReturnAllRegistrations() throws Exception {
        this.mockMvc.perform(get(URI))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnAllRegistrationsOfStudent() throws Exception {
        String URI_LIST_STUDENT = URI + URL_LIST + URL_STUDENT;
        String STUDENT_ID = "2";
        String LASTNAME = "Koelpin";
        String FIRSTNAME = "Andrea";
        this.mockMvc.perform(get(URI_LIST_STUDENT).param("studentId", STUDENT_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].student.firstName").value(FIRSTNAME))
                .andExpect(jsonPath("$[0].student.lastName").value(LASTNAME))
                .andExpect(jsonPath("$[0].courseId").exists())
                .andExpect(jsonPath("$[0].studentId").exists());
    }

    @Test
    public void shouldReturnAllRegistrationsOfCourse() throws Exception {
        String URI_LIST_COURSE = URI + URL_LIST + URL_COURSE;
        String COURSE_ID = "5";
        String COURSE_NAME = "Biology";
        this.mockMvc.perform(get(URI_LIST_COURSE).param("courseId", COURSE_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].course.name").value(COURSE_NAME))
                .andExpect(jsonPath("$[0].courseId").exists())
                .andExpect(jsonPath("$[0].studentId").exists());
    }


    @Test
    public void shouldRegister() throws Exception {

        final Long COURSE_ID = 3L;
        final Long STUDENT_ID = 10L;
        final List<Long> courseIdList = List.of(1L,2L,3L,4L,5L);

        CourseRegistrationDTO dto = CourseRegistrationDTO.builder()
                .courseId(COURSE_ID)
                .studentId(STUDENT_ID)
                .registeredAt(new Date())
                .grade(5)
                .build();

        String inputJson = mapToJson(dto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post(API_PREFIX + REGISTRATIONS)
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}
