package de.neuefische.ffmjava221.teamprojekt.backend.Guest;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public class GuestService {

    private final GuestRepo guestRepo;

    public GuestService(GuestRepo guestRepo) {
        this.guestRepo = guestRepo;
    }

    public Guest addGuestData(NewGuest newGuest) {
        String uuid = UUID.randomUUID().toString();
//        System.out.println(uuid);
        Guest guest = new Guest(uuid, newGuest.firstName(), newGuest.lastName(), newGuest.email(), newGuest.password(), newGuest.confirmPassword());
        return guestRepo.addGuestData(guest);
    }

    public List<Guest> getGuestList() {
        return guestRepo.getGuestList();
    }
}
