package de.neuefische.ffmjava221.teamprojekt.backend.placement;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PlacementIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DirtiesContext
    public void getAllPlacementsWithEmptyList() throws Exception {
        String content = mvc.perform(MockMvcRequestBuilders.get("/api/placements"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<Placement> response = objectMapper.readValue(content, new TypeReference<>() {
        });

        assertTrue(response.isEmpty());
    }

}
