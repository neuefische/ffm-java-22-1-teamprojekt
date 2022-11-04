package de.neuefische.ffmjava221.teamprojekt.backend.login;

import de.neuefische.ffmjava221.teamprojekt.backend.guest.Guest;
import de.neuefische.ffmjava221.teamprojekt.backend.guest.GuestRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class LoginServiceTest {
    private final GuestRepository guestRepository = mock(GuestRepository.class);
    private final LoginService loginService = new LoginService(guestRepository);

    @Test
    void checkLoginDataWithCorrectLoginDataReturnsGuest() {
        //given
        Guest guest = new Guest("Steven", "Lang", "fsagfg@gmail.com", "SuperSecret344$$", "2");
        LoginData loginData = new LoginData("fsagfg@gmail.com","SuperSecret344$$");
        when(guestRepository.findByEmail(loginData.email())).thenReturn(guest);
        //when
        Guest actual = loginService.checkLoginData(loginData);
        //then
        verify(guestRepository).findByEmail(loginData.email());
        assertEquals(guest,actual);
    }

    @Test
    void checkLoginDataWithWrongPasswordReturnsException(){
        //given
        Guest guest = new Guest("Steven", "Lang", "fsagfg@gmail.com", "SuperSecret344$$", "2");
        LoginData loginData = new LoginData("fsagfg@gmail.com","SuperSecret344");
        when(guestRepository.findByEmail(loginData.email())).thenReturn(guest);
        //when
        try{
            loginService.checkLoginData(loginData);
            Assertions.fail();
        } catch(WrongLoginDataException e) {
            //then
            verify(guestRepository).findByEmail(loginData.email());
            String expected = "Wrong Email or Password";
            assertEquals(expected,e.getMessage());
        }
    }

    @Test
    void checkLoginDataWithWrongEmailReturnsException(){
        //given
        LoginData loginData = new LoginData("falsch@gmail.com","SuperSecret344$$");
        when(guestRepository.findByEmail(loginData.email())).thenReturn(null);
        //when
        try{
            loginService.checkLoginData(loginData);
            Assertions.fail();
        } catch(WrongLoginDataException e) {
            //then
            verify(guestRepository).findByEmail(loginData.email());
            String expected = "Wrong Email or Password";
            assertEquals(expected,e.getMessage());
        }
    }
}