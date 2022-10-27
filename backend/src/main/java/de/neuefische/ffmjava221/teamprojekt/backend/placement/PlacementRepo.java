package de.neuefische.ffmjava221.teamprojekt.backend.placement;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PlacementRepo {

    private final Map<String, Placement> placementMap = new HashMap<>();


    public List<Placement> getAllPlacement() {
        return new ArrayList<>(placementMap.values());
    }


    public Placement addNewPlacement(Placement newPlacement) {
        placementMap.put(newPlacement.id(), newPlacement);
        return newPlacement;
    }
}
