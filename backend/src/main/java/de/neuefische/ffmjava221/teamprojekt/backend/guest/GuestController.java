package de.neuefische.ffmjava221.teamprojekt.backend.guest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

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
        try {
            if (guest.id().equals(id)) {
                return guestService.updateGuestById(id, guest);
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public Guest deleteGuestById(@PathVariable String id) {
        try {
            Guest guest = guestService.deleteGuestById(id);
            return guest;
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
