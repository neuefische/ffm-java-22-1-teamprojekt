package de.neuefische.ffmjava221.teamprojekt.backend.placement;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/placements")
@RestController
public class PlacementController {
    private final PlacementService placementService;

    public PlacementController(PlacementService placementService) {
        this.placementService = placementService;
    }

    @GetMapping()
    public List<Placement> getAllPlacement() {
        return placementService.getAllPlacements();
    }

    @PostMapping()
    public Placement addNewPlacement(@RequestBody NewPlacementData requestPlacement) {
        return placementService.addNewPlacement(requestPlacement);
    }
}

