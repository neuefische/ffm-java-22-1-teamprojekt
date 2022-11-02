package de.neuefische.ffmjava221.teamprojekt.backend.guest;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class GuestService {

    private final GuestRepository guestRepository;
    private GuestUtils guestUtils;

    public GuestService(GuestRepository guestRepository, GuestUtils guestUtils) {
        this.guestRepository = guestRepository;
        this.guestUtils = guestUtils;
    }

    public Guest addGuestData(NewGuest newGuest) {
        String uuid = this.guestUtils.generateUUID();
        Guest guest = new Guest(newGuest.firstName(), newGuest.lastName(), newGuest.email(), newGuest.password(), newGuest.confirmPassword(), uuid);
        return this.guestRepository.save(guest);
    }

    public List<Guest> getGuestList() {
        return this.guestRepository.findAll();
    }

    public Guest updateGuestById(String id, Guest guest) {
        List<Guest> guests = guestRepository.findAll();
        for (Guest person : guests) {
            if (person.id().equals(id)) {
                guestRepository.save(person);
                return guest;
            }
        }
        throw new NoSuchElementException("No guest was found with this id");
    }

    public Guest deleteGuestById(String id) {
        Optional<Guest> guestToFind = guestRepository
                .findAll()
                .stream()
                .filter(guest -> guest.id().equals(id))
                .findFirst();
        if (guestToFind.isEmpty()) {
            throw new NoSuchElementException("Element with this Id not found");
        }
        Guest guest = guestToFind.get();
        guestRepository.deleteById(id);
        return guest;
    }


}
