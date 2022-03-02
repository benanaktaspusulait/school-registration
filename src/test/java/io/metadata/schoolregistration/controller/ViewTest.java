package io.metadata.schoolregistration.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import static io.metadata.schoolregistration.constant.Constants.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
public class ViewTest extends AbstractControllerTest {

    private final String URI = API_PREFIX + URL_REGISTRATIONS;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void shouldReturnAllStudentsWithoutCourses() throws Exception {

        String URI_STUDENTS_WITHOUT_COURSES = URI + URL_LIST + STUDENTS_WITHOUT_COURSES;

        this.mockMvc.perform(get(URI_STUDENTS_WITHOUT_COURSES))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].firstName").exists())
                .andExpect(jsonPath("$[0].lastName").exists());
    }

    @Test
    public void shouldReturnAllCoursesWithoutStudents() throws Exception {

        String URI_COURSES_WITHOUT_STUDENTS = URI + URL_LIST + COURSES_WITHOUT_STUDENT;

        this.mockMvc.perform(get(URI_COURSES_WITHOUT_STUDENTS))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].name").exists());
    }


}
