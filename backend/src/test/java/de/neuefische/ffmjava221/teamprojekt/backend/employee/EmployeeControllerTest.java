package de.neuefische.ffmjava221.teamprojekt.backend.employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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

}