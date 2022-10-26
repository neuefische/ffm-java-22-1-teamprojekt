package de.neuefische.ffmjava221.teamprojekt.backend.placement;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PlacementIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DirtiesContext
    void getAllPlacementsWithEmptyList() throws Exception {
        String content = mvc.perform(MockMvcRequestBuilders.get("/api/placements"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<Placement> response = objectMapper.readValue(content, new TypeReference<>() {
        });

        assertTrue(response.isEmpty());
    }

    @Test
    @DirtiesContext
    void postNewPlacement() throws Exception {
        String requestPlacement = """
                {
                "placementNr":5,
                "totalSeats":8
                }
                """;

        String content = mvc.perform(MockMvcRequestBuilders.post("/api/placements").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestPlacement))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Placement newPlacement = objectMapper.readValue(content, Placement.class);

        assertFalse(newPlacement.id().isEmpty());
        assertEquals(5, newPlacement.placementNr());
        assertEquals(8, newPlacement.totalSeats());
    }


}
