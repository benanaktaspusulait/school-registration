package io.metadata.schoolregistration.controller;

import com.github.javafaker.Faker;
import io.metadata.schoolregistration.model.dto.StudentDTO;
import io.metadata.schoolregistration.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static io.metadata.schoolregistration.constant.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
public class StudentControllerTest extends AbstractControllerTest {

    private final String URI = API_PREFIX + URL_STUDENTS;
    private final String LIST_URI = URI + URL_LIST;
    private final String FIRSTNAME = "Noah";
    private final String LASTNAME = "Goyette";

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Mock
    private StudentService studentService;

    private final Faker faker = new Faker();

    @Test
    public void shouldReturnAndConvertAllStudents() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(LIST_URI)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        StudentDTO[] studentList = super.mapFromJson(content, StudentDTO[].class);
        assertTrue(studentList.length > 0);
    }

    @Test
    public void shouldReturnAllStudents() throws Exception {
        this.mockMvc.perform(get(URI))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotReturnStudentFromService() throws Exception {
        when(studentService.findAll(any(), any())).thenReturn(List.of(StudentDTO.builder().firstName("Noah").lastName("Goyette").build()));
        this.mockMvc.perform(get(LIST_URI)
                .contentType("application/json")
                .param("firstName", FIRSTNAME)
                .param("lastName", LASTNAME))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value(FIRSTNAME))
                .andExpect(jsonPath("$[0].lastName").value(LASTNAME))
                .andExpect(jsonPath("$[0].id").exists());
    }

    @Test
    public void shouldCreateStudent() throws Exception {
        StudentDTO student = StudentDTO.builder().firstName(faker.name().firstName()).lastName(faker.name().lastName()).build();
        String inputJson = mapToJson(student);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post(API_PREFIX + URL_STUDENTS)
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldStudentWithAnId() throws Exception {
        List<StudentDTO> studentList = studentService.findAll();
        if (studentList.size() > 0) {
            Long studentId = studentList.get(0).getId();
            this.mockMvc.perform(MockMvcRequestBuilders.delete(API_PREFIX + URL_STUDENTS + "/{id}", studentId))
                    .andExpect(status().isAccepted());
        }
    }
}

