package de.neuefische.ffmjava221.teamprojekt.backend.Guest;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService {

    private final GuestRepo guestRepo;

    public GuestService(GuestRepo guestRepo) {
        this.guestRepo = guestRepo;
    }

    public Guest addGuestData(Guest guest) {
        return guestRepo.addGuestData(guest);
    }

    public List<Guest> getGuestList() {
        return guestRepo.getGuestList();
    }
}
