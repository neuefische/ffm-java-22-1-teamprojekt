package de.neuefische.ffmjava221.teamprojekt.backend.login;

import de.neuefische.ffmjava221.teamprojekt.backend.guest.Guest;
import de.neuefische.ffmjava221.teamprojekt.backend.guest.GuestRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class AppUserServiceTest {
    private final GuestRepository guestRepository = mock(GuestRepository.class);
    private final AppUserService appUserService = new AppUserService(guestRepository);

    @Test
    void checkLoginDataWithCorrectLoginDataReturnsGuest() {
        //given
        Guest guest = new Guest("Steven", "Lang", "fsagfg@gmail.com", "SuperSecret344$$", "2");
        AppUser appUser = new AppUser("fsagfg@gmail.com","SuperSecret344$$");
        when(guestRepository.findByEmail(appUser.email())).thenReturn(guest);
        //when
        Guest actual = appUserService.checkLoginData(appUser);
        //then
        verify(guestRepository).findByEmail(appUser.email());
        assertEquals(guest,actual);
    }

    @Test
    void checkLoginDataWithWrongPasswordReturnsException(){
        //given
        Guest guest = new Guest("Steven", "Lang", "fsagfg@gmail.com", "SuperSecret344$$", "2");
        AppUser appUser = new AppUser("fsagfg@gmail.com","SuperSecret344");
        when(guestRepository.findByEmail(appUser.email())).thenReturn(guest);
        //when
        try{
            appUserService.checkLoginData(appUser);
            Assertions.fail();
        } catch(WrongLoginDataException e) {
            //then
            verify(guestRepository).findByEmail(appUser.email());
            String expected = "Wrong Email or Password";
            assertEquals(expected,e.getMessage());
        }
    }

    @Test
    void checkLoginDataWithWrongEmailReturnsException(){
        //given
        AppUser appUser = new AppUser("falsch@gmail.com","SuperSecret344$$");
        when(guestRepository.findByEmail(appUser.email())).thenReturn(null);
        //when
        try{
            appUserService.checkLoginData(appUser);
            Assertions.fail();
        } catch(WrongLoginDataException e) {
            //then
            verify(guestRepository).findByEmail(appUser.email());
            String expected = "Wrong Email or Password";
            assertEquals(expected,e.getMessage());
        }
    }
}