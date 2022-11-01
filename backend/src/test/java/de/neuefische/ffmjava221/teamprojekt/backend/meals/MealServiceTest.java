package de.neuefische.ffmjava221.teamprojekt.backend.meals;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MealServiceTest {
    private final MealRepository mealRepository = mock(MealRepository.class);
    private final MealService mealService = new MealService(mealRepository);

    @Test
    void getAllMealsAndExpectEmptyList() {
        // GIVEN
        List<Meal> meals = new ArrayList<>();

        // WHEN
        when(mealRepository.findAll()).thenReturn(meals);
        List<Meal> actual = mealService.getAllMeals();
        List<Meal> expected = meals;

        // THEN
        assertEquals(expected, actual);
    }

    @Test
    void addMealWithoutIdAndReturnMealWithId() {
        // GIVEN
        String id = "123";
        Meal newMeal = new Meal(null, "Wurst");
        Meal newMealWithId = new Meal(id, "Wurst");

        when(mealRepository.save(newMeal)).thenReturn(newMealWithId);

        // WHEN
        Meal actual = mealService.saveMeal(newMeal);
        Meal expected = newMealWithId;
        // THEN
        assertEquals(expected, actual);
    }

    @Test
    void deleteMealWithExistingId() {
        // given
        Meal meal = new Meal("123", "Wurst");

        // when
        when(mealRepository.existsById(meal._id())).thenReturn(true);
        doNothing().when(mealRepository).deleteById(meal._id());

        // then
        mealService.deleteMeal((meal._id()));
        verify(mealRepository).deleteById((meal._id()));
    }

    @Test
    void deleteMealWithDoesNotExistingId() {
        // given
        Meal meal = new Meal("123", "Wurst");

        // when
        when(mealRepository.existsById(meal._id())).thenReturn(false);
        doNothing().when(mealRepository).deleteById(meal._id());

        // then
        verify(mealRepository, times(0)).deleteById(meal._id());

    }
}
