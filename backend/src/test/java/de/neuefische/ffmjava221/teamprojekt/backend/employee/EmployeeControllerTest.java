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
                .andExpect(status().isCreated())
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
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        Employee employee = objectMapper.readValue(content, Employee.class);
        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/employees/" + employee.id()))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    {
                        "id": "<id>",
                        "name": "Hasi"
                    }
                    """.replace("<id>", employee.id())));
    }

    @DirtiesContext
    @Test
    void updateEmployeeWithBeforePostEmployeeAndChangedNameRabbitToHoppel() throws Exception {
        //given
        String content = mockMvc.perform(MockMvcRequestBuilders.post("/api/employees")

                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                        {
                                            "name": "Rabbit"
                                        }
                                        """))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        Employee employee = objectMapper.readValue(content, Employee.class);
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    [{
                        "id": "<id>",
                        "name": "Rabbit"
                    }]
                    """.replace("<id>", employee.id())));

     mockMvc.perform(MockMvcRequestBuilders.put("/api/employees/" + employee.id())

                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                                        {
                                            "id": "<id>",
                                            "name": "Hoppel"
                                        }
                                        """.replace("<id>", employee.id())))
            .andExpect(status().isCreated())
            .andReturn().getResponse().getContentAsString();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    [{
                        "id": "<id>",
                        "name": "Hoppel"
                    }]
                    """.replace("<id>", employee.id())));
    }
}
