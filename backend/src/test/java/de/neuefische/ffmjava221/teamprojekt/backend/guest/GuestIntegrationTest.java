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
                                 "password": "SuperSecret344$$",
                                 "confirmPassword": "SuperSecret344$$"}
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
                                 "password": "SuperSecret344$$",
                                 "confirmPassword": "SuperSecret344$$",
                                 "id" : "<id>"}]
                                """.replace("<id>", guest.id())));
    }

    @Test
    @DirtiesContext
    void addGuestFalsePassword() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/guests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"firstName": "test",
                                 "lastName": "test",
                                 "email": "test@gmail.com",
                                 "password": "password",
                                 "confirmPassword": "password"}
                                """))
                .andExpect(status().isBadRequest());
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
                                "password": "SuperSecret344$$",
                                "confirmPassword": "SuperSecret344$$",
                                "id" :  "<id>"}
                               
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
                                 "password": "SuperSecret344$$",
                                 "confirmPassword": "SuperSecret344$$",
                                 "id" :  "<id>"},
                                 {"firstName": "Armin",
                                 "lastName": "test",
                                 "email": "test@gmail.com",
                                 "password": "SuperSecret344$$",
                                 "confirmPassword": "SuperSecret344$$",
                                 "id" :  "id"}
                                """.replace("<id>", guest.id()))))
                // THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                                         {"firstName": "Simon",
                                         "lastName": "test",
                                         "email": "test@gmail.com",
                                         "password": "SuperSecret344$$",
                                         "confirmPassword": "SuperSecret344$$",
                                         "id" :  "<id>"},
                                         {"firstName": "Armin",
                                         "lastName": "test",
                                         "email": "test@gmail.com",
                                         "password": "SuperSecret344$$",
                                         "confirmPassword": "SuperSecret344$$",
                                         "id" :  "id"}
                        """.replace("<id>", guest.id())));
    }

    @Test
    @DirtiesContext
    void putRequestUpdateGuestDataWithBadRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/api/guests/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"firstName": "test",
                                "lastName": "test",
                                "email": "test@gmail.com",
                                "password": "SuperSecret344$$",
                                "confirmPassword": "SuperSecret344$$",
                                "id" :  "<id>"}
                                    """))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DirtiesContext
    void putRequestUpdateGuestMethodNotAllowed() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/api/guests/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"firstName": "test",
                                "lastName": "test",
                                "email": "test@gmail.com",
                                "password": "SuperSecret344$$",
                                "confirmPassword": "SuperSecret344$$",
                                "id" :  "<id>"}
                                    """))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    @DirtiesContext
    void putRequestUpdateGuestNotFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/api/guests/1337")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"firstName": "test",
                                "lastName": "test",
                                "email": "test@gmail.com",
                                "password": "SuperSecret344$$",
                                "confirmPassword": "SuperSecret344$$",
                                "id" :  "1337"}
                                    """))
                .andExpect(status().isNotFound());
    }

    @Test
    @DirtiesContext
    void deleteGuestByIdIsSuccessful() throws Exception {

        String body = mvc.perform(MockMvcRequestBuilders.post("/api/guests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"firstName": "test",
                                 "lastName": "test",
                                 "email": "test@gmail.com",
                                "password": "SuperSecret344$$",
                                 "confirmPassword": "SuperSecret344$$"}
                                """))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Guest guest = objectMapper.readValue(body, Guest.class);

        mvc
                .perform(MockMvcRequestBuilders.delete("/api/guests/" + guest.id()))
                .andExpect(status().isOk())
                .andExpect(content().json(body));
    }

    @Test
    @DirtiesContext
    void deleteGuestByIdNotFound() throws Exception {

        mvc.perform(MockMvcRequestBuilders.delete("/api/guests/954ujfew90ru30rfi033")).andExpect(status().isNotFound());
    }
}



