package de.neuefische.ffmjava221.teamprojekt.backend.placement;


import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PlacementService {


    private final PlacementRepository placementRepository;


    public PlacementService(PlacementRepository placementRepository) {
        this.placementRepository = placementRepository;
    }

    public List<Placement> getAllPlacements() {
        return placementRepository.findAll();
    }

    public Placement addNewPlacement(NewPlacementData newRequestPlacement) {

        String newUuid = PlacementUtil.generateUuid();

        int placementNr = newRequestPlacement.placementNr();
        int totalSeats = newRequestPlacement.totalSeats();

        Placement newPlacement = new Placement(newUuid, placementNr, totalSeats);
        placementRepository.save(newPlacement);

        return newPlacement;

    }

    public Placement checkIfExist(String placementId) {
        Optional<Placement> placementToFind = placementRepository.findById(placementId);

        if (placementToFind.isEmpty()) {
            throw new NoSuchElementException("Placement not Exist!");
        }
        return placementToFind.get();
    }


    public Placement updatePlacement(String placementId, Placement newData) {
        Placement existPlacement = checkIfExist(placementId);

        Placement updatedPlacement = new Placement(existPlacement.id(), existPlacement.placementNr(), newData.totalSeats());
        placementRepository.save(updatedPlacement);
        return updatedPlacement;
    }

    public void deletePlacement(String placementId) {
        if (placementRepository.existsById(placementId)) {
            placementRepository.deleteById(placementId);
        } else {
            throw new NoSuchElementException("Placement not Exist!");
        }
    }

    public void reserveNewPlacement(String placementId, ReserveTimeRequest reserveData) {
        Optional<Placement> placementToFind = placementRepository.findById(placementId);

        if (placementToFind.isEmpty()) {
            throw new NoSuchElementException("No Placement with this id!!");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startDate = LocalDateTime.parse(reserveData.reserveTime(), formatter);
        LocalDateTime endDate = startDate.plus(Duration.ofHours(reserveData.duration()));


        String newId = PlacementUtil.generateUuid();
        Reservation newReservation = new Reservation(newId, reserveData.reserveTime(), endDate.toString(), reserveData.guestId());

        Map<String, Reservation> reservationsMap = placementToFind.get().reservations();
        reservationsMap.put(newReservation.id(), newReservation);

        placementRepository.save(placementToFind.get());
    }
}

//
