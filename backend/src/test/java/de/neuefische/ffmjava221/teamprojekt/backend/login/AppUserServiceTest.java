package de.neuefische.ffmjava221.teamprojekt.backend.login;

import de.neuefische.ffmjava221.teamprojekt.backend.SecurityConfig;

import net.bytebuddy.description.type.TypeList;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class AppUserServiceTest {
    /*
    private final AppUserRepository appUserRepository = mock(AppUserRepository.class);
    private final SecurityConfig securityConfig = mock(SecurityConfig.class);
    private final AppUserService appUserService = new AppUserService(appUserRepository, securityConfig);
    private final PasswordEncoder mockEncoder = mock(PasswordEncoder.class);

    @Test
    void findByUsernameAndReturnUsername() {
        // given
        AppUser newAppUser = new AppUser("1",
                "Bob",
                "password",
                null,
                null,
                null,
                null,
                null
        );

        when(appUserRepository.findByUsername("Bob")).thenReturn(newAppUser);

        // when
        AppUser actual = appUserService.findByUsername("Bob");

        // then
        AppUser expected = newAppUser;
        assertEquals(expected, actual);
    }


    @Test
    void addUserAndReturnAppUser() {
        //given
        AppUser newAppUser = new AppUser("1",
                "Bob",
                "rawpassword",
                null,
                null,
                null,
                null,
                null
        );

        when(securityConfig.encoder()).thenReturn(mockEncoder);
        AppUser encodedAppUser = newAppUser.withPassword(mockEncoder.encode(newAppUser.password()));
        when(mockEncoder.encode((newAppUser.password()))).thenReturn("encodedByBCrypt");
        when(appUserRepository.save(newAppUser.withPassword("encodedByBCrypt"))).thenReturn(encodedAppUser);
        when(appUserRepository.findByUsername(newAppUser.username())).thenReturn(null);
        //when
        AppUser actual = appUserService.addUser(newAppUser);
        AppUser expected = encodedAppUser;
        //then
        assertEquals(expected, actual);
    }

    @Test
    void addUserReturnsUserAlreadyExistsException() {
        //given
        String username = "testuser";
        AppUser newAppUser = new AppUser("1",
                username,
                "rawpassword",
                null,
                null,
                null,
                null,
                null
        );
        when(appUserRepository.findByUsername(username)).thenReturn(newAppUser);
        //when
        try {
            appUserService.addUser(newAppUser);
            fail();
        } catch (UserAlreadyExistsException e) {
            //then
            assertEquals("User with this name already exists", e.getMessage());
        }
    }
     */
}