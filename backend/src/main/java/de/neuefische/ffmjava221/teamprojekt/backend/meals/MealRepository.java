package de.neuefische.ffmjava221.teamprojekt.backend.meals;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class MealRepository {
    List<Meal> meals = new ArrayList<>();


    public List<Meal> getAllMeals() {
        return meals;
    }

    public Meal addMeal(Meal newMeal) {
        meals.add(newMeal);
        return newMeal;
    }

    public Meal updateMeal(int index, Meal meal) {
        meals.set(index, meal);
        return meal;
    }

    public Meal deleteMeal(int index) {
        return meals.remove(index);
    }
}