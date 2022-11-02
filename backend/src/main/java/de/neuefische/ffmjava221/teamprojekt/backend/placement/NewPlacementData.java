package de.neuefische.ffmjava221.teamprojekt.backend.placement;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public record NewPlacementData(
        @NotNull(message = "PlacementNr must not be Null")
        int placementNr,
        @NotNull
        @Min(value = 2, message = "Seats must be between 2 and 15")
        @Max(value=15, message = "Seats must be between 2 and 15")
        int totalSeats) {
}

