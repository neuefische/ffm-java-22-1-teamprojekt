package de.neuefische.ffmjava221.teamprojekt.backend.guest;

import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class GuestService {

    private final GuestRepo guestRepo;
    private GuestUtils guestUtils;

    public GuestService(GuestRepo guestRepo, GuestUtils guestUtils) {
        this.guestRepo = guestRepo;
        this.guestUtils = guestUtils;
    }

    public Guest addGuestData(NewGuest newGuest) {
        String uuid = this.guestUtils.generateUUID();
        Guest guest = new Guest(newGuest.firstName(), newGuest.lastName(), newGuest.email(), newGuest.password(), newGuest.confirmPassword(), uuid);
        return this.guestRepo.addGuestData(guest);
    }

    public List<Guest> getGuestList() {
        return this.guestRepo.getGuestList();
    }
}
