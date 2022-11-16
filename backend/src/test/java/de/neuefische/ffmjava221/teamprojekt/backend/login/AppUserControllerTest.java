package de.neuefische.ffmjava221.teamprojekt.backend.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.neuefische.ffmjava221.teamprojekt.backend.guest.Guest;
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
class AppUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DirtiesContext
    void loginDataWithCorrectCredentialsReturnsGuest() throws Exception{

        String body = mockMvc.perform(MockMvcRequestBuilders.post("/api/guests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                 "firstName": "test",
                                 "lastName": "test",
                                 "email": "test@gmail.com",
                                 "password": "SuperSecret344$$"
                                 }
                                """))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Guest guest = objectMapper.readValue(body, Guest.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "email": "test@gmail.com",
                        "password": "SuperSecret344$$"
                    }
                    """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    {
                         "firstName": "test",
                         "lastName": "test",
                         "email": "test@gmail.com",
                         "password": "SuperSecret344$$",
                         "id": "<id>"
                     }
                    """.replace("<id>", guest.id())));
    }

    @Test
    @DirtiesContext
    void loginDataWithWrongPasswordReturnsError() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api/guests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                 "firstName": "test",
                                 "lastName": "test",
                                 "email": "test@gmail.com",
                                 "password": "SuperSecret344$$"
                                 }
                                """))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                        "email": "test@gmail.com",
                        "password": "falsch"
                    }
                    """))
                .andExpect(status().isNotFound())
                .andExpect(status().reason("Wrong Email or Password"));
    }
    @Test
    @DirtiesContext
    void loginDataWithWrongEmailReturnsError() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api/guests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                 "firstName": "test",
                                 "lastName": "test",
                                 "email": "test@gmail.com",
                                 "password": "SuperSecret344$$"
                                 }
                                """))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                        "email": "falsch@gmail.com",
                        "password": "SuperSecret344$$"
                    }
                    """))
                .andExpect(status().isNotFound())
                .andExpect(status().reason("Wrong Email or Password"));
    }
}