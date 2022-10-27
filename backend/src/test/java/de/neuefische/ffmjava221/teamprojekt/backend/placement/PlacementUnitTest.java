package de.neuefische.ffmjava221.teamprojekt.backend.placement;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


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
                new Placement(UUID.randomUUID().toString(), 1, 5),
                new Placement(UUID.randomUUID().toString(), 2, 3),
                new Placement(UUID.randomUUID().toString(), 3, 3),
                new Placement(UUID.randomUUID().toString(), 4, 5),
                new Placement(UUID.randomUUID().toString(), 5, 2)
        ));

        when(testRepo.getAllPlacement()).thenReturn(placements);

        List<Placement> result = testService.getAllPlacements();

        assertArrayEquals(placements.toArray(), result.toArray());
    }

    @Test
    void addNewPlacement() {
        // GIVEN
        NewPlacementData newData = new NewPlacementData(5, 2);
        // WHEN
        Placement result = testService.addNewPlacement(newData);
        // THEN
        assertFalse(result.id().isEmpty());
    }

    @Test
    void updatingNewPlacementServiceWithNotExistPlacement() {
        // Given
        Placement placementToTest = new Placement("123", 4, 5);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            testService.updatePlacement("123", placementToTest);
        });
        String expectedMessage = "Placement not Exist!";
        //When


        String actualMessage = exception.getMessage();
        //Then


        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void updatingNewPlacementServiceWithExist() {
        //GIVEN
        List<Placement> allPlacements = new ArrayList<>(List.of(
                new Placement("123", 4, 2),
                new Placement("435435", 8, 5),
                new Placement("gf5646546", 2, 4),
                new Placement("gjfas43566", 1, 5),
                new Placement("123gsgs5435", 3, 2)
        ));

        when(testRepo.getAllPlacement()).thenReturn(allPlacements);

        Placement updatedData = new Placement("123", 4, 8);
        //WHEN
        Placement placementAfterUpdate = testService.updatePlacement("123", updatedData);
        // THEN
        assertEquals(8, placementAfterUpdate.totalSeats());
    }

    @Test
    void deletePlacementWithNotExistId() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            testService.deletePlacement("123");
        });
        String expectedMessage = "Placement not Exist!";
        //When
        String actualMessage = exception.getMessage();
        //Then
        assertTrue(actualMessage.contains(expectedMessage));
        //Give
    }

    @Test
    void deletePlacementWithExistId() {
        List<Placement> allPlacements = new ArrayList<>(List.of(
                new Placement("123", 4, 2),
                new Placement("435435", 8, 5),
                new Placement("gf5646546", 2, 4),
                new Placement("gjfas43566", 1, 5),
                new Placement("123gsgs5435", 3, 2)
        ));

        when(testRepo.getAllPlacement()).thenReturn(allPlacements);

        boolean deleteResult = testService.deletePlacement("gjfas43566");

        assertTrue(deleteResult);

    }
}

