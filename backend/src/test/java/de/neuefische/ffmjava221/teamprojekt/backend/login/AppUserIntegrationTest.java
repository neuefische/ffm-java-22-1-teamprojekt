package de.neuefische.ffmjava221.teamprojekt.backend.login;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AppUserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void expect200_GET_login() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/login"))
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser
    void expect200_GET_me() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/me"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"EMPLOYEE"})
    void expect200_GET_role_asEmployee() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/role"))
                .andExpect(status().isOk())
                .andExpect(content().string("[ROLE_EMPLOYEE]"));
    }

    @Test
    @WithMockUser(roles = {"GUEST"})
    void expect200_GET_role_asGuest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/role"))
                .andExpect(status().isOk())
                .andExpect(content().string("[ROLE_GUEST]"));
    }

    @Test
    @DirtiesContext
    @WithMockUser(roles = {"GUEST"})
    void expect201_POST_addGuest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/guest")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "username": "ira",
                                    "password": "Password898#",
                                    "firstName": "ira",
                                    "lastName": "test",
                                    "email": "ira@test.com"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("ira"))
                .andExpect(jsonPath("$.firstName").value("ira"))
                .andExpect(jsonPath("$.lastName").value("test"))
                .andExpect(jsonPath("$.email").value("ira@test.com"))
                .andExpect(jsonPath("$.role").value("GUEST"))
                .andExpect(jsonPath("$.password").exists())
                .andExpect(jsonPath("$.regTimeStamp").exists());
    }

    @Test
    @DirtiesContext
    @WithMockUser(roles = {"GUEST"})
    void expect409_conflict_POST_addGuest_WithAlreadyExistingName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/guest")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "username": "ira",
                                    "password": "Password898#",
                                    "firstName": "ira",
                                    "lastName": "test",
                                    "email": "ira@test.com"
                                }
                                """))
                .andExpect(status().isCreated());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/guest")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "username": "ira",
                                    "password": "Password898#",
                                    "firstName": "ira",
                                    "lastName": "test",
                                    "email": "ira@test.com"
                                }
                                """))
                .andExpect(status().isConflict())
                .andExpect(status().reason("User with this name already exists"));
    }

    @Test
    @DirtiesContext
    @WithMockUser(roles = {"EMPLOYEE"})
    void expect201_POST_addEmployee() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "username": "ira",
                                    "password": "Password898#",
                                    "firstName": "ira",
                                    "lastName": "test"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("ira"))
                .andExpect(jsonPath("$.firstName").value("ira"))
                .andExpect(jsonPath("$.lastName").value("test"))
                .andExpect(jsonPath("$.role").value("EMPLOYEE"))
                .andExpect(jsonPath("$.password").exists())
                .andExpect(jsonPath("$.regTimeStamp").exists());
    }

    @Test
    @DirtiesContext
    @WithMockUser(roles = {"EMPLOYEE"})
    void expect409_conflict_POST_addEmployee_WithAlreadyExistingName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "username": "ira",
                                    "password": "Password898#"
                                }
                                """))
                .andExpect(status().isCreated());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "username": "ira",
                                    "password": "Password898#"
                                }
                                """))
                .andExpect(status().isConflict())
                .andExpect(status().reason("User with this name already exists"));
    }

    @Test
    @DirtiesContext
    @WithMockUser(roles = {"GUEST"})
    void expect403_POST_addEmployeeAsGuest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "username": "ira",
                                    "password": "Password898#",
                                    "firstName": "ira",
                                    "lastName": "test"
                                }
                                """))
                .andExpect(status().isForbidden())
                .andExpect(status().reason("Forbidden"));
    }
    @Test
    @DirtiesContext
    void expect401_POST_addEmployeeWithoutLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "username": "ira",
                                    "password": "Password898#",
                                    "firstName": "ira",
                                    "lastName": "test"
                                }
                                """))
                .andExpect(status().isUnauthorized())
                .andExpect(status().reason("Unauthorized"));
    }
}