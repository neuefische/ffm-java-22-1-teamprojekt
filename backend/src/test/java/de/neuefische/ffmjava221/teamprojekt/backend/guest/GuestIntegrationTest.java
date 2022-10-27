package de.neuefische.ffmjava221.teamprojekt.backend.guest;

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
class GuestIntegrationTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @DirtiesContext
    @Test
    void addGuest() throws Exception {
        // GIVEN

        String body = mvc.perform(MockMvcRequestBuilders.post("/api/guests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"firstName": "test",
                                 "lastName": "test",
                                 "email": "test@gmail.com",
                                 "password": "test",
                                 "confirmPassword": "test"}
                                """))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Guest guest = objectMapper.readValue(body, Guest.class);

        // WHEN

        mvc.perform(MockMvcRequestBuilders.get("/api/guests/"))

                // THEN

                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                                [{"firstName": "test",
                                 "lastName": "test",
                                 "email": "test@gmail.com",
                                 "password": "test",
                                 "confirmPassword": "test",
                                 "id" : "<id>"}]
                                """.replace("<id>", guest.id())));
    }

    @DirtiesContext
    @Test
    void getAllGuestsAndExpectEmptyList() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/guests"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }


    @Test
    @DirtiesContext
    void putRequestUpdateGuestData() throws Exception {

        // GIVEN

        String body = mvc.perform(MockMvcRequestBuilders.post("/api/guests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"firstName": "test",
                                 "lastName": "test",
                                 "email": "test@gmail.com",
                                 "password": "test",
                                 "confirmPassword": "test",
                                 "id" :  "id"}
                                """))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Guest guest = objectMapper.readValue(body, Guest.class);

        // WHEN
        mvc.perform(MockMvcRequestBuilders.put("/api/guests/" + guest.id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(("""
                                 {"firstName": "Simon",
                                 "lastName": "test",
                                 "email": "test@gmail.com",
                                 "password": "test",
                                 "confirmPassword": "test",
                                 "id" :  "<id>"}
                                """.replace("<id>", guest.id()))))
                // THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                                         {"firstName": "Simon",
                                         "lastName": "test",
                                         "email": "test@gmail.com",
                                         "password": "test",
                                         "confirmPassword": "test",
                                         "id" :  "<id>"}
                        """.replace("<id>", guest.id())));
    }
}

