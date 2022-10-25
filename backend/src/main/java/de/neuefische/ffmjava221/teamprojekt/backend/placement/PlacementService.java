package de.neuefische.ffmjava221.teamprojekt.backend.placement;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  PlacementService {
 
    private final PlacementRepo placementRepo ;

    public PlacementService(PlacementRepo placementRepo) {
        this.placementRepo = placementRepo;
    }

    public List<Placement> getAllPlacements(){
               return placementRepo.getAllPlacement();
    }
}
