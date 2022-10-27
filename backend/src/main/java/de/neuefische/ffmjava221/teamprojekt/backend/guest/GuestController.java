package de.neuefische.ffmjava221.teamprojekt.backend.guest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

//// Put nur für Vorname und Nachname
//    @PutMapping("/{id}")
//    public ResponseEntity<Guest> updateGuest(@PathVariable String id, @RequestBody Guest guest){
//        if (guest.id().equals(id)) {
//            int index = guestService.getIndexOfId(id);
//            Guest newGuest = guestService.updateGuest(index, guest);
//            boolean guestExists = (index>=0);
//            return guestExists ? new ResponseEntity<>(newGuest,HttpStatus.OK) : new ResponseEntity<>(newGuest,HttpStatus.CREATED);
//        } return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }

    @PutMapping("{id}")
    public Guest updateGuestById(@PathVariable String id, @RequestBody Guest guest) {
        return guestService.updateGuestById(id, guest);
    }

}
