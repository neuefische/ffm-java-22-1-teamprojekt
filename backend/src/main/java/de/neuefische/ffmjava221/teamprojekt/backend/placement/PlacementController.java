package de.neuefische.ffmjava221.teamprojekt.backend.placement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
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
    public Placement addNewPlacement(@Valid @RequestBody NewPlacementData requestPlacement) {
        return placementService.addNewPlacement(requestPlacement);
    }


    @PutMapping("{placementId}")
    public ResponseEntity<Placement> updatePlacement(@PathVariable String placementId, @RequestBody @Valid Placement newData) {
        try {
            Placement updatedPlacement = placementService.updatePlacement(placementId, newData);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(updatedPlacement);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID is not found!");
        }
    }

    @DeleteMapping("{placementId}")
    public void deletePlacement(@PathVariable String placementId) {
        try {
            placementService.deletePlacement(placementId);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID is not found!");
        }
    }
}

