package de.neuefische.ffmjava221.teamprojekt.backend.placement;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PlacementRepo {

    private final Map<String,Placement> placementMap = new HashMap<>();

    public List<Placement> getAllPlacement(){
       return new ArrayList<>(placementMap.values());
    }
}
