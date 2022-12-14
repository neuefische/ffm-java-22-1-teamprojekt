package de.neuefische.ffmjava221.teamprojekt.backend.placement;


import org.springframework.stereotype.Service;

import java.util.List;
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
}
