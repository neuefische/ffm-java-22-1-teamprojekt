package de.neuefische.ffmjava221.teamprojekt.backend.placement;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlacementService {

    private final PlacementRepo placementRepo;


    public PlacementService(PlacementRepo placementRepo) {
        this.placementRepo = placementRepo;
    }

    public List<Placement> getAllPlacements() {
        return placementRepo.getAllPlacement();
    }

    public Placement addNewPlacement(NewPlacementData newRequestPlacement) {

        String newUuid = PlacementUtil.generateUuid();
        int placementNr = newRequestPlacement.placementNr();
        int totalSeats = newRequestPlacement.totalSeats();

        Placement newPlacement = new Placement(newUuid, placementNr, totalSeats);
        placementRepo.addNewPlacement(newPlacement);

        return newPlacement;
    }


}
