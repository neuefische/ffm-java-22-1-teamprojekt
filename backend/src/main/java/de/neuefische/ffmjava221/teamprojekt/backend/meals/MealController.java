package de.neuefische.ffmjava221.teamprojekt.backend.meals;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

//    @PostMapping
//    @ResponseStatus(code = HttpStatus.CREATED)
//    public Meal addMeal(@Valid @RequestBody Meal newMeal) {
//        return mealService.saveMeal(newMeal);
//    }

    @PutMapping()
    public ResponseEntity<Meal> updateMeal(@Valid @RequestBody Meal newMeal) {
        Meal createdMeal = mealService.saveMeal(newMeal);
        return createdMeal._id().equals(newMeal._id()) ?
                new ResponseEntity<>(createdMeal, HttpStatus.OK) :
                new ResponseEntity<>(createdMeal, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteMeal(@PathVariable String id) {
       mealService.deleteMeal(id);
    }
}
