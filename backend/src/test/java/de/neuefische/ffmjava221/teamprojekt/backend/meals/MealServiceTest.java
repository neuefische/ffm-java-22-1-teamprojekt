package de.neuefische.ffmjava221.teamprojekt.backend.meals;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MealServiceTest {
    private final MealRepository mealRepository = mock(MealRepository.class);
    private final ServiceUtils serviceUtils = mock(ServiceUtils.class);
    private final MealService mealService = new MealService(mealRepository,serviceUtils);

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

    @Test
    void addMealWithoutIdAndReturnMealWithId() {
        // GIVEN
        String uuid = "123";
        Meal newMeal = new Meal(null,"Wurst");
        Meal newMealWithId = new Meal(uuid,"Wurst");

        when(serviceUtils.generateUUID()).thenReturn(uuid);

        when(mealRepository.addMeal(newMealWithId)).thenReturn(newMealWithId);

        // WHEN
        Meal actual = mealService.addMeal(newMeal);
        Meal expected = newMealWithId;
        verify(serviceUtils).generateUUID();
        // THEN
        assertEquals(expected, actual);
    }
}
