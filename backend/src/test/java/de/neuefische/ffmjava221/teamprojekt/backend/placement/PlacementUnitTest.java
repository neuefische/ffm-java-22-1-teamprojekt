package de.neuefische.ffmjava221.teamprojekt.backend.placement;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class PlacementUnitTest {

    private final PlacementRepo testRepo = mock(PlacementRepo.class);
    private final PlacementService testService = new PlacementService(testRepo);

    @Test
    void getAllPlacementsAtBeginning() {
        when(testRepo.getAllPlacement()).thenReturn(new ArrayList<>());

        List<Placement> result = testService.getAllPlacements();

        assertTrue(result.isEmpty());
    }

    @Test
    void getAllPlacementsWithExistPlacements() {
        List<Placement> placements = new ArrayList<>(List.of(
                new Placement(UUID.randomUUID().toString(), 1),
                new Placement(UUID.randomUUID().toString(), 2),
                new Placement(UUID.randomUUID().toString(), 3),
                new Placement(UUID.randomUUID().toString(), 4),
                new Placement(UUID.randomUUID().toString(), 5)
        ));

        when(testRepo.getAllPlacement()).thenReturn(placements);

        List<Placement> result = testService.getAllPlacements();

        assertArrayEquals(placements.toArray(), result.toArray());
    }
}

