package de.neuefische.ffmjava221.teamprojekt.backend.Guest;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guests")
public class GuestController {

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @PostMapping
    Guest postGuest(@RequestBody Guest guest) {
        return guestService.addGuestData(guest);
    }

    @GetMapping
    List<Guest> getGuestList() {
        return guestService.getGuestList();
    }
}
