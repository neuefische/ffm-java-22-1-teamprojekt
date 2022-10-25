package de.neuefische.ffmjava221.teamprojekt.backend.meals;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MealService {

    private final MealRepository mealRepository;
    private final ServiceUtils serviceUtils;

    public MealService(MealRepository mealRepository, ServiceUtils serviceUtils) {
        this.mealRepository = mealRepository;
        this.serviceUtils = serviceUtils;
    }

    public List<Meal> getAllMeals(){
        return mealRepository.getAllMeals();
    }


    public Meal addMeal(Meal newMeal) {
        String id = serviceUtils.generateUUID();
        return mealRepository.addMeal(newMeal.withId(id));
    }
}
