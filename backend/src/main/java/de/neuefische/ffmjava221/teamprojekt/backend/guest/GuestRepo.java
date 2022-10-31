package de.neuefische.ffmjava221.teamprojekt.backend.guest;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    public void deleteGuestById(String id) {

        Optional<Guest>  guestToFind = guests.stream().filter(guest -> guest.id().equals(id)).findFirst();

        if (guestToFind.isEmpty()) {
            throw new NoSuchElementException("Element with this Id not found");
        }
        guests.remove(guestToFind.get());

    }
}
