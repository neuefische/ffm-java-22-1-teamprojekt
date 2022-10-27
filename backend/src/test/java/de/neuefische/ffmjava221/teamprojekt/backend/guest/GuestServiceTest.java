package de.neuefische.ffmjava221.teamprojekt.backend.guest;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GuestServiceTest {

    @Test
    public void getGuestList() {
        //GIVEN

        GuestRepo guestRepo = mock(GuestRepo.class);
        List<Guest> guests = new ArrayList<>();
        Guest guest = new Guest("Steven", "Lang", "fsagfg@gmail.com", "hallo", "hallo", "123");
        guests.add(guest);

        //WHEN

        when(guestRepo.getGuestList()).thenReturn(guests);
        List<Guest> actual = guestRepo.getGuestList();

        //THEN

        assertEquals(guests, actual);
    }

    @Test
    void addGuestWithID() {

        //GIVEN

        GuestRepo guestRepo = mock(GuestRepo.class);
        GuestUtils guestUtils = mock(GuestUtils.class);
        GuestService guestService = new GuestService(guestRepo, guestUtils);

        NewGuest newGuest = new NewGuest("Steven", "Lang", "fggfl@gmail.de", "TestPost", "TestPost");
        Guest testGuest = newGuest.withId("2");

        Guest expected = testGuest;
        when(guestRepo.addGuestData(testGuest)).thenReturn(testGuest);
        when(guestUtils.generateUUID()).thenReturn("2");

        //WHEN

        Guest actual = guestService.addGuestData(newGuest);

        //THEN

        verify(guestUtils).generateUUID();
        assertEquals(expected, actual);
    }
    @Test
    void updateGuestById() {

        //GIVEN

        GuestUtils guestId = mock(GuestUtils.class);
        GuestRepo guestRepo = mock(GuestRepo.class);
        GuestService guestService = new GuestService(guestRepo, guestId);

        List<Guest> guests = new ArrayList<>();
        Guest guest = new Guest("Steven", "Lang", "fsagfg@gmail.com", "hallo", "hallo","2" );
        Guest updatedGuest = new Guest("Robert", "Lang", "fsagfg@gmail.com", "hallo", "hallo", "2");
        guests.add(guest);

        //WHEN

        when(guestRepo.getGuestList()).thenReturn(guests);
        Guest actual = guestService.updateGuestById("2", updatedGuest);

        //THEN

        assertEquals(updatedGuest, actual);
    }
}
