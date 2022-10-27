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
    void getAllMealsAndExpectEmptyList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/meals"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    @DirtiesContext
    void addMealWithoutIdAndReturnMeal() throws Exception {
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
                        """.replace("<id>", meal.id())));
    }

    @Test
    @DirtiesContext
    void updateMealWithExistingIdAndExpect200() throws Exception {
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

        mockMvc.perform(MockMvcRequestBuilders.put("/api/meals/" + meal.id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "id": "<id>",
                                    "name": "Banane"
                                }
                                """.replace("<id>", meal.id())))
                .andExpect(status().is(200))
                .andExpect(content().json("""
                        {
                            "id": "<id>",
                            "name": "Banane"
                        }
                        """.replace("<id>", meal.id())));
    }

    @Test
    @DirtiesContext
    void updateMealWithoutIdAndExpect201() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/meals/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "id": "1",
                                    "name": "Banane"
                                }
                                """))
                .andExpect(status().is(201))
                .andExpect(content().json("""
                        {
                            "id": "1",
                            "name": "Banane"
                        }
                        """));
    }

    @Test
    @DirtiesContext
    void updateMealWithNotMatchingIdAndExpect400() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/meals/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "id": "1",
                                    "name": "Banane"
                                }
                                """))
                .andExpect(status().is(400));
    }
    @Test
    @DirtiesContext
    void deleteMealSuccesfullAndReturnMealToDelete() throws Exception {
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

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/meals/"+meal.id()))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    {
                        "id": "<id>",
                        "name": "Wurst"
                    }
                    """.replace("<id>",meal.id())));
    }

    @Test
    @DirtiesContext
    void deleteMealWithWrongIdReturns400() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/meals/123"))
                .andExpect(status().is(400));
    }
}
