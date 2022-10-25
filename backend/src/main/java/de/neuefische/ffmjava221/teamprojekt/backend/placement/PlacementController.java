package de.neuefische.ffmjava221.teamprojekt.backend.placement;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/placements")
@Controller
public class PlacementController {

    private final PlacementService placementService;

    public PlacementController(PlacementService placementService) {
        this.placementService = placementService;
    }

    @GetMapping()
    public List<Placement> getAllPlacement() {
        return placementService.getAllPlacements();
    }
}
