package de.neuefische.ffmjava221.teamprojekt.backend.placement;
import org.springframework.data.annotation.PersistenceCreator;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashMap;

import java.util.Map;


public record Placement(
        String id,
        @NotNull(message = "PlacementNr must not be null")
        int placementNr,
        @Min(value = 2)
        @Max(15)
        @NotNull
        int totalSeats,
        Map<String, String> startTimesReservation
) {
    public Placement(String id, int placementNr, int totalSeats) {
        this(id, placementNr, totalSeats, new HashMap<>());
    }

    @PersistenceCreator
    public Placement(String id, int placementNr, int totalSeats,Map<String,String>startTimesReservation) {
        this.id = id;
        this.placementNr=placementNr;
        this.totalSeats=totalSeats;
        this.startTimesReservation=startTimesReservation;
    }


}


