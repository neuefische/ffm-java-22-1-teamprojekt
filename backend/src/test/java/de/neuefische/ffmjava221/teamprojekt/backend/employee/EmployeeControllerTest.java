package de.neuefische.ffmjava221.teamprojekt.backend.employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @DirtiesContext
    @Test
    void getAllEmployeesTEST() throws Exception{

        //GIVEN & WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees"))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @DirtiesContext
    @Test
    void addEmployeeReturnsEmployeeWithID() throws Exception {
        //given
        String content = mockMvc.perform(MockMvcRequestBuilders.post("/api/employees")

                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                        {
                                            "name": "Hasi"
                                        }
                                        """))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Employee employee = objectMapper.readValue(content, Employee.class);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees"))
                //then
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    [{
                        "id": "<id>",
                        "name": "Hasi"
                    }]
                    """.replace("<id>", employee.id())));
    }

    @DirtiesContext
    @Test
    void deleteEmployeeReturnsEmptyEmployee() throws Exception {
        //given
        String content = mockMvc.perform(MockMvcRequestBuilders.post("/api/employees")

                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                        {
                                            "name": "Hasi"
                                        }
                                        """))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println("Content: " + content);
        Employee employee = objectMapper.readValue(content, Employee.class);
        System.out.println("employee: " + employee);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees"))
                //then
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    [{
                        "id": "<id>",
                        "name": "Hasi"
                    }]
                    """.replace("<id>", employee.id())));

        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/employees/" + employee.id()))
                .andExpect(status().isOk())
                .andExpect(content().string("Hasi erfolgreich gelöscht"));
    }
}