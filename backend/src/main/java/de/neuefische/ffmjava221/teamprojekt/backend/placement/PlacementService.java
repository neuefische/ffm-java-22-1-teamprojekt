package de.neuefische.ffmjava221.teamprojekt.backend.placement;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Placement updatePlacement(String placementId,Placement newData) {

        List<Placement> allPlacement = placementRepo.getAllPlacement();
        Optional<Placement> placementToFind = allPlacement.stream()
                .filter(placement -> placement.id().equals(placementId))
                .findFirst();

        if(placementToFind.isEmpty()){
            throw new IllegalArgumentException("Placement not Exist!");
        }

        Placement  existPlacement =placementToFind.get();
        Placement updatedPlacement =new Placement(existPlacement.id(),existPlacement.placementNr(),newData.totalSeats());

        placementRepo.updatePlacement(existPlacement,updatedPlacement);
        return updatedPlacement;
    }

}
