package de.neuefische.ffmjava221.teamprojekt.backend.meals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MealIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllMealsAndExpectEmptyList() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/meals"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
}