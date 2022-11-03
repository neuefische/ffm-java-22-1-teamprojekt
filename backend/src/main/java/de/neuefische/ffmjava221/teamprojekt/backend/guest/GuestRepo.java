package de.neuefische.ffmjava221.teamprojekt.backend.guest;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GuestRepo {

    private List<Guest> guests = new ArrayList<>();

    public Guest addGuestData(Guest guest) {
        guests.add(guest);
        return guest;
    }

    public List<Guest> getGuestList() {
        return this.guests;
    }

    public void setGuest(int index, Guest guest) {
        guests.set(index, guest);
    }

    public Guest deleteGuestById(Guest guest) {
        guests.remove(guest);
        return guest;
    }
}