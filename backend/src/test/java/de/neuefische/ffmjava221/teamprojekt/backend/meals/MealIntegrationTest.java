package de.neuefische.ffmjava221.teamprojekt.backend.meals;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MealIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllMealsAndExpectEmptyList() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/meals"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    @DirtiesContext
    void addMealWithoutIdAndReturnMeal() throws Exception  {
        String body = mockMvc.perform(MockMvcRequestBuilders.post("/api/meals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "name": "Wurst"
                            }
                            """))
                .andExpect(status().is(201))
                .andReturn().getResponse().getContentAsString();

        Meal meal = objectMapper.readValue(body, Meal.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/meals"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    [{
                        "id": "<id>",
                        "name": "Wurst"
                    }]
                    """.replace("<id>",meal.id())));
    }
}
