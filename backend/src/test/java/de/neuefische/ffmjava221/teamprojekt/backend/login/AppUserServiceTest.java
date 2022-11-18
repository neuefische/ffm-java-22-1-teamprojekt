package de.neuefische.ffmjava221.teamprojekt.backend.login;



import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class AppUserServiceTest {

    private final AppUserRepository appUserRepository = mock(AppUserRepository.class);
    private final AppUserService appUserService = new AppUserService(appUserRepository);

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


  /*  @Test
    void addUserAndReturnAppUser() {
        //given
        AppUser newAppUser = new AppUser("1",
                "Bob",
                "rawPassword",
                null,
                null,
                null,
                null,
                null
        );

        try (MockedStatic<SecurityConfig> securityConfig = Mockito.mockStatic(SecurityConfig.class)){
            AppUser encodedAppUser = newAppUser.withPassword("encodedPassword");
            securityConfig.when(() -> SecurityConfig.passwordEncoder.encode("rawPassword")).thenReturn("encodedPassword");
            AppUser actual = appUserService.addUser(newAppUser);
            AppUser expected = encodedAppUser;
            //then
            assertEquals(expected, actual);
            //assertThat(SecurityConfig.passwordEncoder.encode("rawPassword")).containsExactly("encodedPassword");
        }*/


/*        when(securityConfig.encoder()).thenReturn(mockEncoder);
        when(mockEncoder.encode((newAppUser.password()))).thenReturn("encodedByBCrypt");
        when(appUserRepository.save(newAppUser.withPassword("encodedByBCrypt"))).thenReturn(encodedAppUser);
        when(appUserRepository.findByUsername(newAppUser.username())).thenReturn(null);*/
        //when




//    }

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

}