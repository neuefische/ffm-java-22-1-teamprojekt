package de.neuefische.ffmjava221.teamprojekt.backend.Guest;

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
}

