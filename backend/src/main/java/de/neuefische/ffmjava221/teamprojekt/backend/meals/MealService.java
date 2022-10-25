package de.neuefische.ffmjava221.teamprojekt.backend.meals;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealService {

    private final MealRepository mealRepository;

    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public List<Meal> getAllMeals(){
        return mealRepository.getAllMeals();
    }


}
