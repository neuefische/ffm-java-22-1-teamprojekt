package de.neuefische.ffmjava221.teamprojekt.backend.meals;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meals")
public class MealController {

    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping
    public List<Meal> getAllMeals() {
        return mealService.getAllMeals();
    }

    @PostMapping
    public ResponseEntity<Meal> addMeal(@RequestBody Meal newMeal) {
        return new ResponseEntity<Meal>(mealService.addMeal(newMeal), HttpStatus.CREATED);
    }
}

