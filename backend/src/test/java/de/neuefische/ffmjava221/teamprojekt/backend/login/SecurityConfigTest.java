package de.neuefische.ffmjava221.teamprojekt.backend.login;

import de.neuefische.ffmjava221.teamprojekt.backend.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SecurityConfigTest {
    private final AppUserService appUserService = mock(AppUserService.class);
    private final SecurityConfig securityConfig = new SecurityConfig(appUserService);

    @Test
    void findByUsernameReturnsAppUser() {
        // given
        String rawPassword = "user123";
        String encodedPassword = new BCryptPasswordEncoder().encode(rawPassword);
        AppUser appUser = new AppUser(
                "id1",
                "user",
                encodedPassword,
                null,
                null,
                null,
                null,
                AppUserRole.GUEST
        );
        // when
        when(appUserService.findByUsername(appUser.username())).thenReturn(appUser);
        String actual = securityConfig.userDetailsManager()
                .loadUserByUsername(appUser.username())
                .getPassword();
        // then
        String expected = encodedPassword;
        assertEquals(expected, actual);
        assertTrue(securityConfig.encoder().matches(rawPassword, actual));
    }
    @Test
    void findByUsernameReturnsError() {
        // given
        String username = "testuser";
        when(appUserService.findByUsername(username)).thenReturn(null);
        // when
        try {
            securityConfig.userDetailsManager().loadUserByUsername(username).getPassword();
            fail();
        } catch (UsernameNotFoundException e) {
            verify(appUserService).findByUsername(username);
            assertEquals("Username " + username + " not found", e.getMessage());
        }
    }

}
