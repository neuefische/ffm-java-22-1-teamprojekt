package de.neuefische.ffmjava221.teamprojekt.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class CatIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @DirtiesContext
    @Test
    void shouldReturnIdFromUrl_whenIdIsDifferentInUrlAndBody() throws Exception {
        mockMvc.perform(put("/api/cats/123")
                        .header(CONTENT_TYPE, APPLICATION_JSON)
                        .content("""
                                    {
                                      "id": "99999999999",
                                      "name": "Minki"
                                    }
                                """))
                .andExpect(
                        content()
                                .json("""
                                            {
                                              "id": "123",
                                              "name": "Minki"
                                            }
                                        """)
                );
    }

}
