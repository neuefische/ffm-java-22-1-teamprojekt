package de.neuefische.ffmjava221.teamprojekt.backend.employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;


    @DirtiesContext
    @Test
    void getProductBananaFromProduct() throws Exception{

        //GIVEN & WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees"))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                                    [
                                        {
                                            "id": "1",
                                            "name": "Marc"
                                        },
                                        {
                                            "id": "2",
                                            "name": "Chris"
                                        },
                                        {
                                            "id": "3",
                                            "name": "Dennis"
                                        }
                                    ]
                                    """));
    }



//    @Test
//    void postEmployee() throws Exception{
//        //GIVEN
//        String expected = objectMapper.writeValueAsString(List.of(
//                new EmployeeCard("1","Marc"),
//                new EmployeeCard("2","Chris"),
//                new EmployeeCard("3","Dennis")));
//        //WHEN
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees"))
//        //THEN
//                .andExpect(status().isOk())
//                .andExpect(content().json(expected));
//    }
}