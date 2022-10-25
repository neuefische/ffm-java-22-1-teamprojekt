package de.neuefische.ffmjava221.teamprojekt.backend.meals;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MealServiceTest {
    private final MealRepository mealRepository = mock(MealRepository.class);
    private final MealService mealService = new MealService(mealRepository);

    @Test
    void getAllMealsAndExpectEmptyList() {
        // GIVEN
        List<Meal> meals = new ArrayList<>();

        // WHEN
        when(mealRepository.getAllMeals()).thenReturn(meals);
        List<Meal> actual = mealService.getAllMeals();
        List<Meal> expected = meals;

        // THEN
        assertEquals(expected, actual);
    }
}
