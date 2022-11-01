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

    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }


    public Meal saveMeal(Meal meal) {
        return mealRepository.save(meal);
    }

//    public Meal updateMeal(int index, Meal newMeal) {
//        return mealRepository.sa(index, newMeal);
//    }

//    public int getIndexOfId(String id) {
//        List<Meal> meals = mealRepository.getAllMeals();
//        for (Meal meal : meals) {
//            if (meal.id().equals(id)) {
//                return meals.indexOf(meal);
//            }
//        }
//        return -1;
//    }

    public void deleteMeal(String id) {
         mealRepository.deleteById(id);
    }
}
