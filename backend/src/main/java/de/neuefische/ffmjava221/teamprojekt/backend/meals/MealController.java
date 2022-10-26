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
    @ResponseStatus(code = HttpStatus.CREATED)
    public Meal addMeal(@RequestBody Meal newMeal) {
        return mealService.addMeal(newMeal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Meal> updateMeal(@PathVariable String id, @RequestBody Meal meal){

        int index = mealService.getIndexOfId(id);
        Meal newMeal = mealService.updateMeal(index, meal);
        boolean mealExists = (index>=0);
        return mealExists ? new ResponseEntity<>(newMeal,HttpStatus.OK) : new ResponseEntity<>(newMeal,HttpStatus.CREATED);
    }
}

