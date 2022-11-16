package de.neuefische.ffmjava221.teamprojekt.backend.login;

import de.neuefische.ffmjava221.teamprojekt.backend.SecurityConfig;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class AppUserServiceTest {
    private final AppUserRepository appUserRepository = mock(AppUserRepository.class);
    private final SecurityConfig securityConfig = mock(SecurityConfig.class);
    private final AppUserService appUserService = new AppUserService(appUserRepository, securityConfig);



    @Test
    void addUserAndReturnAppUser() {
        //given
        AppUser newAppUser = new AppUser("1",
                "Bob",
                "Password25#",
                null,
                null,
                null,
                null,
                null
                );

        AppUser encodedAppUser = newAppUser.withPassword("encoded");
        when(securityConfig.passwordEncoder.encode(newAppUser.password())).thenReturn("encoded");
        when(appUserRepository.save(encodedAppUser)).thenReturn(newAppUser);
        //when
        AppUser actual = appUserService.addUser(newAppUser);
        AppUser expected = newAppUser;
        //then
        assertEquals(expected,actual);
    }


}