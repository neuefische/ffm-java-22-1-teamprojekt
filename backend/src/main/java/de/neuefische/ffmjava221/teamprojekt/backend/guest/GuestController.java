package de.neuefische.ffmjava221.teamprojekt.backend.guest;

import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/guests")
public class GuestController {

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @PostMapping
    Guest addGuest(@RequestBody @Valid NewGuest guest) {
        return guestService.addGuestData(guest);
    }

    @GetMapping
    List<Guest> getGuestList() {
        return guestService.getGuestList();
    }

    @PutMapping("{id}")
    public Guest updateGuestById(@PathVariable String id, @RequestBody Guest guest) {
        return guestService.updateGuestById(id, guest);
    }
}
