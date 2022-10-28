package de.neuefische.ffmjava221.teamprojekt.backend.placement;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


public record Placement(
        String id,
        @NotNull(message = "PlacementNr must not be null")
        int placementNr,
        @Min(value = 2)
        @Max(15)
        @NotNull
        int totalSeats
) {
}
