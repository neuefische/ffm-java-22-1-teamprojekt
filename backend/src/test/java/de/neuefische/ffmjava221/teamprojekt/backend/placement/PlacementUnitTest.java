package de.neuefische.ffmjava221.teamprojekt.backend.placement;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class PlacementUnitTest {

    private final PlacementRepository testRepo = mock(PlacementRepository.class);
    private final PlacementService testService = new PlacementService(testRepo);

    @Test
    void getAllPlacementsAtBeginning() {
        when(testRepo.findAll()).thenReturn(new ArrayList<>());

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

        when(testRepo.findAll()).thenReturn(placements);

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

        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            testService.updatePlacement("123", placementToTest);
        });
        String expectedMessage = "Placement not Exist!";
        //When


        String actualMessage = exception.getMessage();
        //Then


        assertTrue(actualMessage.contains(expectedMessage));


    }

    @Test
    void updatingNewPlacementServiceWithExistId() {
        //GIVEN
        Placement testRecord = new Placement("123", 4, 2);


        Placement updatedData = new Placement("123", 4, 8);

        when(testRepo.save(updatedData)).thenReturn(updatedData);

        when(testRepo.findById("123")).thenReturn(Optional.of(testRecord));
        //WHEN
        Placement placementAfterUpdate = testService.updatePlacement("123", updatedData);
        // THEN
        assertEquals(8, placementAfterUpdate.totalSeats());
        assertEquals(4, placementAfterUpdate.placementNr());
    }

    @Test
    void deletePlacementWithNotExistId() {
        //Give
        when(testRepo.existsById("123")).thenReturn(false);
        //when
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            testService.deletePlacement("123");
        });
        String expectedMessage = "Placement not Exist!";
        String actualMessage = exception.getMessage();
        //Then
        assertTrue(actualMessage.contains(expectedMessage));


    }

    @Test
    void deletePlacementWithExistId() {

        Placement testRecord = new Placement("53lk2h532kh5", 4, 2);

        when(testRepo.existsById(testRecord.id())).thenReturn(true);

        testService.deletePlacement(testRecord.id());

        verify(testRepo).deleteById("53lk2h532kh5");
    }
}

