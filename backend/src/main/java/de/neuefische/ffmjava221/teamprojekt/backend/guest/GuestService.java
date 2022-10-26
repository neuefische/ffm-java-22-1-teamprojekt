package de.neuefische.ffmjava221.teamprojekt.backend.guest;

import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class GuestService {

    private final GuestRepo guestRepo;
    private ServiceUtils serviceUtils;

    public GuestService(GuestRepo guestRepo, ServiceUtils serviceUtils) {
        this.guestRepo = guestRepo;
        this.serviceUtils = serviceUtils;
    }

    public Guest addGuestData(NewGuest newGuest) {
        String uuid = this.serviceUtils.generateUUID();
        Guest guest = new Guest(newGuest.firstName(), newGuest.lastName(), newGuest.email(), newGuest.password(), newGuest.confirmPassword(), uuid);
        return this.guestRepo.addGuestData(guest);
    }

    public List<Guest> getGuestList() {
        return this.guestRepo.getGuestList();
    }
}
