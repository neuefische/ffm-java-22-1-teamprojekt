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
        NewMeal newMeal = new NewMeal("Wurst");
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

    @Test
    void updateMealWithIndexMinus1ReturnsNewMeal() {
        // GIVEN
        Meal newMeal = new Meal("1","Wurst");
        when(mealRepository.addMeal(newMeal)).thenReturn(newMeal);

        // WHEN
        Meal actual = mealService.updateMeal(-1, newMeal);

        // THEN
        assertEquals(newMeal, actual);
    }

    @Test
    void updateMealWithIndex0ReturnsNewMeal() {
        // GIVEN
        Meal newMeal = new Meal("1","Wurst");
        when(mealRepository.updateMeal(0,newMeal)).thenReturn(newMeal);

        // WHEN
        Meal actual = mealService.updateMeal(0, newMeal);

        // THEN
        assertEquals(newMeal, actual);
    }

    @Test
    void getIndexOfIdReturnsMinus1() {
        // GIVEN
        List<Meal> meals = new ArrayList<>();
        String id = "123";
        when(mealRepository.getAllMeals()).thenReturn(meals);
        // WHEN
        int actual = mealService.getIndexOfId(id);
        int expected = -1;
        // THEN
        assertEquals(expected, actual);
    }

    @Test
    void getIndexOfIdReturns0() {
        // GIVEN
        List<Meal> meals = new ArrayList<>(List.of(new Meal("1","Wurst")));
        String id = "1";
        when(mealRepository.getAllMeals()).thenReturn(meals);
        // WHEN
        int actual = mealService.getIndexOfId(id);
        int expected = 0;
        // THEN
        assertEquals(expected, actual);
    }
    @Test
    void deleteMeal() {
        // GIVEN
        Meal mealtoDelete = new Meal("1","Wurst");

        when(mealRepository.getAllMeals()).thenReturn(new ArrayList<>(List.of(mealtoDelete)));
        when(mealRepository.deleteMeal(0)).thenReturn(mealtoDelete);
        // WHEN
        Meal actual = mealService.deleteMeal("1");
        Meal expected = mealtoDelete;
        // THEN
        assertEquals(expected, actual);
    }
}
