package de.neuefische.ffmjava221.teamprojekt.backend;

import de.neuefische.ffmjava221.teamprojekt.backend.Guest.Guest;
import de.neuefische.ffmjava221.teamprojekt.backend.Guest.GuestRepo;
import de.neuefische.ffmjava221.teamprojekt.backend.Guest.GuestService;
import de.neuefische.ffmjava221.teamprojekt.backend.Guest.NewGuest;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GuestServiceTest {


    @Test
    public void addGuestDataTest() {
        //Given

        GuestRepo guestRepo = mock(GuestRepo.class);
        GuestService guestService = new GuestService(guestRepo);
        Guest guest = new Guest("Steven", "Lang", "fsagfg@gmail.com", "hallo", "hallo","123");
       NewGuest newGuest = new NewGuest("Steven", "Lang", "fsagfg@gmail.com", "hallo", "hallo");
        //When

        when(guestRepo.addGuestData(guest)).thenReturn(guest);
        Guest actual = guestService.addGuestData(newGuest);

        //Then

        assertEquals(guest, actual);
    }



    @Test
    public void getGuestList(){
        //GIVEN

        GuestRepo guestRepo = mock(GuestRepo.class);
        List<Guest> guests = List.of(
                new Guest("Steven", "Lang", "fsagfg@gmail.com", "hallo", "hallo","123"));

        //WHEN
        List<Guest> actual = guestRepo.getGuestList();

        //THEN
        assertEquals(guests, actual);
    }
}
